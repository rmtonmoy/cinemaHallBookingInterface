package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.dto.PasswordAndVerificationDto;
import com.example.CinemaEbookingSystem.dto.PasswordDto;
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

    @ModelAttribute("resetPassword")
    public PasswordAndVerificationDto passwordAndVerificationDto() {
        return new PasswordAndVerificationDto();
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
            return "redirect:/signin?VerifiedCustomer";
        else
            return "redirect:/verify?UnverifiedCustomer";
    }

    @GetMapping(path = "/sendVerificationCode")
    String sendVC(Model model, HttpSession session)
    {
        return "sendVerificationCode";
    }
    @PostMapping(path = "/sendVerificationCode")
    String sendVC(@ModelAttribute("verifyUser") VerificationDto verificationDto, Model model)
    {
        if(emailService.sendEmailRP(verificationDto.getEmail()))
            return "redirect:/resetPassword";
        else
            return "redirect:/sendVerificationCode?WrongEmail";
    }

    @GetMapping(path = "/resetPassword")
    String resetPassword(Model model, HttpSession session)
    {
        return "resetPassword";
    }
    @PostMapping(path = "/resetPassword") 
    String resetPassword(@ModelAttribute("resetPassword") PasswordAndVerificationDto passwordAndVerificationDto, Model model) 
    {
        if (!emailService.verifyCustomerRP(passwordAndVerificationDto)) {
            return "redirect:/resetPassword?UnverifiedCustomer";
        } else if (!emailService.confirmPassword(passwordAndVerificationDto)) {
            return "redirect:/resetPassword?PasswordsDoNotMatch";
        } else {
            emailService.resetPassword(passwordAndVerificationDto);
            return "redirect:/signin?Success";
        }
    }

}
