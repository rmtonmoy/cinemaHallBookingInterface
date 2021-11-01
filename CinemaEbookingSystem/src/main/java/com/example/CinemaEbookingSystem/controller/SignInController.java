package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.dto.UserSignInDto;
import com.example.CinemaEbookingSystem.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SignInController {

    @Autowired
    private SignInService signInService;

    @ModelAttribute("user")
    public UserSignInDto userSignInDto(){
        return new UserSignInDto();
    }

    @GetMapping(path = "/signin")
    String signin(Model model, HttpSession session){
        model.addAttribute("something", "Cinema E-booking System");
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("userName", session.getAttribute("name"));
        return "signin";
    }

    @PostMapping(path = "/signin")
    String signin(@ModelAttribute("user") UserSignInDto userSignInDto, HttpServletRequest request){
        if(signInService.isAdminWithRightPassword(userSignInDto)){
            request.getSession().setAttribute("email", userSignInDto.getEmail());
            request.getSession().setAttribute("name", signInService.getAdminName(userSignInDto));
            return "redirect:/adminHome";
        }
        else if(signInService.isActiveCustomerWithRightPassword(userSignInDto)){
            request.getSession().setAttribute("email", userSignInDto.getEmail());
            request.getSession().setAttribute("name", signInService.getName(userSignInDto));
            return "redirect:/";
        }
        else if (signInService.isInactiveCustomerWithRightPassword(userSignInDto)) {
            request.getSession().setAttribute("email", userSignInDto.getEmail());
            return "redirect:/signin?InactiveCustomer";
        }

        return "redirect:/signin?WrongPassword";
    }
}
