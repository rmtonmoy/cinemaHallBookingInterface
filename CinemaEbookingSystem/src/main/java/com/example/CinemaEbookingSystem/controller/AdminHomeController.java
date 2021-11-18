package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.dto.MovieInfoDto;
import com.example.CinemaEbookingSystem.dto.UserSignInDto;
import com.example.CinemaEbookingSystem.model.MovieInfo;
import com.example.CinemaEbookingSystem.repository.MovieInfoRepository;
import com.example.CinemaEbookingSystem.service.MovieInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminHomeController {
    @Autowired
    MovieInfoService movieInfoService;

    @ModelAttribute("movieInfoDto")
    public MovieInfoDto getMovieInfoDto(){return new MovieInfoDto();}

    @PostMapping(path = "/manageMovies")
    String addNewMove(@ModelAttribute("movie") MovieInfoDto movieInfoDto, HttpServletRequest request){
        movieInfoService.SaveMovieInfo(new MovieInfo(movieInfoDto));
        return "/mangeMovies";
    }
}
