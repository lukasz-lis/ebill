package pl.eightbit.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.eightbit.Member;
import pl.eightbit.MemberRepository;
import pl.eightbit.dto.MemberDTO;

import javax.validation.Valid;

@Controller
public class AccountController {

    @Autowired
    private MemberRepository memberRepository;

    @RequestMapping(value = "/rejestracja", method = RequestMethod.GET)
    public String goToCreateNewAccount(Model model) {
        model.addAttribute("memberDTO", new MemberDTO());
        return "signup";

    }

    @RequestMapping(value = "/rejestracja", method = RequestMethod.POST)
    public String handleCreationOfNewAccount(@Valid MemberDTO memberDTO, BindingResult bindingResult, Model model) {
        model.addAttribute("memberDTO", memberDTO);

        if (bindingResult.hasErrors()) {
            return "signup";
        }

        Member member = Member.builder() //
                .email(memberDTO.getEmail()) //
                .firstName(memberDTO.getFirstName()) //
                .lastName(memberDTO.getLastName()) //
                .password(memberDTO.getPassword()) //
                .username(memberDTO.getUsername()) //
                .build();

        memberRepository.save(member);
        return "signup-successful";

    }

    @RequestMapping(value = "/logowanie", method = RequestMethod.GET)
    public String login() {
        return "signin";
    }

}
