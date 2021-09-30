package com.example.CinemaEbookingSystem;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class MovieController {

    @GetMapping
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

    @GetMapping(path = "/managePromo")
    String managePromo(Model model){
        model.addAttribute("something", "Cinema E-booking System");
        return "managePromo";
    }

}