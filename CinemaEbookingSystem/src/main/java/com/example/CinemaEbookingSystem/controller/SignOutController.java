package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.model.MovieInfo;
import com.example.CinemaEbookingSystem.service.MovieInfoService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SignOutController {
    @Autowired
    MovieInfoService movieInfoService;

    @GetMapping(path = "/logout")
    String logout(Model model, HttpServletRequest request, HttpSession session){


        model.addAttribute("listCurrentMovie", movieInfoService.listOfCurrentMovies());
        model.addAttribute("listComingSoonMovie", movieInfoService.listOfComingSoonMovies());
        request.getSession().removeAttribute("email");
        return "home";
    }
}
