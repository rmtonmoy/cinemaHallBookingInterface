package com.example.CinemaEbookingSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
public class MovieController {

    @GetMapping(path = "/")
    String homepage(Model model, HttpSession session){
        System.out.println(session.getAttribute("email"));
        System.out.println(session.getAttribute("name"));
        model.addAttribute("something", "Cinema E-booking System");
        model.addAttribute("userName", session.getAttribute("name"));
        model.addAttribute("email", session.getAttribute("email"));
        return "movie";
    }


    @GetMapping(path = "/paymentConfirmation")
    String paymentConfirmation(Model model, HttpSession session){
        model.addAttribute("something", "Cinema E-booking System");
        return "paymentConfirmation";
    }

    // @GetMapping(path = "/signup")
    //String signUp(Model model){
    //    return "signup";
    //}

    @GetMapping(path = "/registration_confirmation")
    String registrationConfirmationMessage(Model model, HttpSession session){
        return "registrationconfirmation";
    }

    @GetMapping(path = "/search")
    String getSearchView(Model model, HttpSession session){
        model.addAttribute("something", "Cinema E-booking System");
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("userName", session.getAttribute("name"));
        return "search_view";
    }

    @GetMapping(path = "/orderHistory")
    String getOrderHistory(Model model, HttpSession session) {
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("userName", session.getAttribute("name"));
        return "Order-History";
    }

    @GetMapping(path = "/orderSummary")
    String orderSummary(Model model, HttpSession session){
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("userName", session.getAttribute("name"));
        model.addAttribute("something", "Cinema E-booking System");
        return "orderSummary";
    }

}