package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.dto.VerificationDto;
import com.example.CinemaEbookingSystem.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class VerificationController {

    @Autowired
    EmailService emailService;

    @ModelAttribute("verifyUser")
    public VerificationDto verificationDto(){
        return new VerificationDto();
    }

    @GetMapping(path = "/verify")
    String verifyCustomer(Model model, HttpSession session)
    {
        model.addAttribute("something", "Cinema E-booking System");
        return "verify";
    }
    @PostMapping(path = "/verify")
    String verifyCustomer(@ModelAttribute("verifyUser") VerificationDto verificationDto, Model model)
    {
        if(emailService.verifyCustomer(verificationDto))
            return "redirect:/verify?VerifiedCustomer";
        else
            return "redirect:/verify?UnverifiedCustomer";
    }

    @ModelAttribute("verifyUserRP")

    @GetMapping(path = "/sendVerificationCode")
    String verifyCustomerRP(Model model, HttpSession session)
    {
        model.addAttribute("something", "Cinema E-booking System");
        return "sendVerificationCode";
    }
    @PostMapping(path = "/sendVerificationCode")
    String verifyCustomerRP(@ModelAttribute("verifyUserRP") VerificationDto verificationDto, Model model)
    {
        if(emailService.sendEmailRP(verificationDto.getEmail()))
            return "redirect:/forgotPassword2";
        else
            return "redirect:/sendVerificationCode?WrongEmail";
    }

}
