package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.service.MovieInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovieInfoController {
    @Autowired
    private MovieInfoService movieInfoService;

    @GetMapping(path = "/")
    public String dummyPage2(Model model){
        model.addAttribute("ListMovieInfo", movieInfoService.getAllMovieInfo());
        return "movie";
    }

}
