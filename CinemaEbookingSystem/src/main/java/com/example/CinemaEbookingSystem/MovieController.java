package com.example.CinemaEbookingSystem;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MovieController {

    @GetMapping("/")
    String getMovie(Model model) {
        model.addAttribute("something", "Cinema E-booking System");
        model.addAttribute("movie", Arrays.asList(
                new Movie("Movie1"),
                new Movie("Movie2"),
                new Movie("Movie3")
        ));
        return "movie";
    }
    @GetMapping(path = "/signin")
    String signin(Model model){
        model.addAttribute("something", "Cinema E-booking System");
        return "signin";
    }

    @GetMapping(path = "/adminHome")
    String adminHome(Model model){
        model.addAttribute("something", "Cinema E-booking System");
        return "adminHome";
    }

    @GetMapping(path = "/manageMovies")
    String manageMovies(Model model){
        model.addAttribute("something", "Cinema E-booking System");
        return "manageMovies";
    }

    @GetMapping(path = "/paymentConfirmation")
    String paymentConfirmation(Model model){
        model.addAttribute("something", "Cinema E-booking System");
        return "paymentConfirmation";
    }

    @GetMapping(path = "/managePromo")
    String managePromo(Model model){
        model.addAttribute("something", "Cinema E-booking System");
        return "managePromo";
    }

    @GetMapping(path = "/signup")
    String signUp(Model model){
        return "signup";
    }

    @GetMapping(path = "/registration_confirmation")
    String registrationConfirmationMessage(Model model){
        return "registrationconfirmation";
    }

    @GetMapping(path = "/EditProfile")
    String getEditProfile() {
        return "Edit-Profile";
    }
}