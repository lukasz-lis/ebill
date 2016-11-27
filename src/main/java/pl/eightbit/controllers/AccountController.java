package pl.eightbit.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.eightbit.dto.MemberWithPasswordDTO;
import pl.eightbit.dto.MemberWithoutPasswordDTO;
import pl.eightbit.services.AccountService;
import pl.eightbit.validators.ConfirmPasswordValidator;
import pl.eightbit.validators.UniqueMemberEmailValidation;
import pl.eightbit.validators.UniqueMemberUsernameValidation;

import javax.validation.Valid;

@Controller
public class AccountController {


    private static final String ACCOUNT_SETTINGS = "account-settings";
    private static final String SIGN_UP = "signup";
    private static final String SIGN_UP_SUCCESSFUL = "signup-successful";

    private final AccountService accountService;
    private final UniqueMemberUsernameValidation usernameValidator;
    private final UniqueMemberEmailValidation emailValidator;
    private final ConfirmPasswordValidator passwordValidator;

    @Autowired
    public AccountController(final AccountService accountService, final UniqueMemberUsernameValidation usernameValidator, final UniqueMemberEmailValidation emailValidator, final ConfirmPasswordValidator passwordValidator) {
        this.accountService = accountService;
        this.usernameValidator = usernameValidator;
        this.emailValidator = emailValidator;
        this.passwordValidator = passwordValidator;
    }

    @InitBinder
    public void initBinder(final WebDataBinder webDataBinder) {
        webDataBinder.addValidators(usernameValidator, emailValidator, passwordValidator);
    }

    @RequestMapping(value = "/rejestracja", method = RequestMethod.GET)
    public String registerMember(final Model model) {
        model.addAttribute("memberWithPasswordDTO", new MemberWithPasswordDTO());
        return SIGN_UP;

    }

    @RequestMapping(value = "/rejestracja", method = RequestMethod.POST)
    public String createMember(@Valid final MemberWithPasswordDTO memberWithPasswordDTO, final BindingResult bindingResult, final Model model) {


        if (bindingResult.hasErrors()) {
            model.addAttribute("memberWithPasswordDTO", memberWithPasswordDTO);
            return SIGN_UP;
        }

        accountService.create(memberWithPasswordDTO);
        return SIGN_UP_SUCCESSFUL;

    }

    @RequestMapping(value = "/ustawienia-konta", method = RequestMethod.GET)
    public String fetchExistingMember(final Model model) {

        final MemberWithoutPasswordDTO memberDTO = accountService.getMemberWithoutPasswordDTO();
        model.addAttribute("memberWithoutPasswordDTO", memberDTO);

        return ACCOUNT_SETTINGS;
    }

    @RequestMapping(value = "/ustawienia-konta", method = RequestMethod.POST)
    public String updateMember(@Valid final MemberWithoutPasswordDTO memberWithoutPasswordDTO, final BindingResult bindingResult, final Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("saveCorrectly", false);
            model.addAttribute("memberWithoutPasswordDTO", memberWithoutPasswordDTO);
            return ACCOUNT_SETTINGS;
        }

        accountService.update(memberWithoutPasswordDTO);

        model.addAttribute("saveCorrectly", true);

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
}
