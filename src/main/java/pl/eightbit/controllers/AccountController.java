package pl.eightbit.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.eightbit.dto.MemberWithPasswordDTO;
import pl.eightbit.dto.MemberWithoutPasswordDTO;
import pl.eightbit.services.AccountService;

import javax.validation.Valid;

@Controller
public class AccountController {


    private static final String ACCOUNT_SETTINGS = "account-settings";
    private static final String SIGN_UP = "signup";
    private static final String SIGN_UP_SUCCESSFUL = "signup-successful";
    private final AccountService accountService;

    @Autowired
    public AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/rejestracja", method = RequestMethod.GET)
    public String registerMember(final Model model) {
        model.addAttribute("memberDTO", new MemberWithPasswordDTO());
        return SIGN_UP;

    }

    @RequestMapping(value = "/rejestracja", method = RequestMethod.POST)
    public String createMember(@Valid final MemberWithPasswordDTO memberDTO, final BindingResult bindingResult, final Model model) {

        if (bindingResult.hasErrors()) {
//            model.addAttribute("memberDTO", memberDTO);
            return SIGN_UP;
        }

        accountService.create(memberDTO);
        return SIGN_UP_SUCCESSFUL;

    }

    @RequestMapping(value = "/ustawienia-konta", method = RequestMethod.GET)
    public String fetchExistingMember(final Model model) {

        final MemberWithoutPasswordDTO memberDTO = accountService.getMemberWithoutPasswordDTO();
        model.addAttribute("memberDTO", memberDTO);

        return ACCOUNT_SETTINGS;
    }

    @RequestMapping(value = "/ustawienia-konta", method = RequestMethod.POST)
    public String updateMember(@Valid final MemberWithoutPasswordDTO memberDTO, final BindingResult bindingResult, final Model model) {
        model.addAttribute("memberDTO", memberDTO);

        model.addAttribute("saveCorrectly", validateAndUpdateMember(memberDTO, bindingResult, model));

        return ACCOUNT_SETTINGS;
    }

    @RequestMapping(value = "/logowanie", method = RequestMethod.GET)
    public String login(final Model model) {
        model.addAttribute("loginError", false);
        return "signin";
    }

    @RequestMapping("/logowanie-blad")
    public String loginError(final Model model) {
        model.addAttribute("loginError", true);
        return "signin";
    }

    private boolean validateAndUpdateMember(@Valid final MemberWithoutPasswordDTO memberDTO, final BindingResult bindingResult, final Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("memberDTO", memberDTO);
            return false;
        }

        accountService.update(memberDTO);
        return true;

    }

}
