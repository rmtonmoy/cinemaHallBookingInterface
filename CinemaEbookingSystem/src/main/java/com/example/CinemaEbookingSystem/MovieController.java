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

    @GetMapping(path = "/signup")
    String signUp(Model model){
        return "signup";
    }

}