package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.dto.MovieSearchDto;
import com.example.CinemaEbookingSystem.model.MovieInfo;
import com.example.CinemaEbookingSystem.service.MovieInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.CinemaEbookingSystem.model.Admin;
import com.example.CinemaEbookingSystem.repository.AdminRepository;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.Arrays;

@Controller
public class MovieController {
    
    @Autowired
    MovieInfoService movieInfoService;
    
    public MovieController(MovieInfoService mis) {
        super();
        movieInfoService = mis;
    }

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
    
    // =================================================================================================================
    // Search & filter
    
    @GetMapping(path = "/search")
    String getSearch(Model model, HttpSession session, @ModelAttribute("searchParams") MovieSearchDto searchParams) {
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("userName", session.getAttribute("name"));
        
        List<String> categories = movieInfoService.getCategories();
        categories.add(0, "Any");
        model.addAttribute("categories", categories);
        
        // TODO: Cooler title match algorithm
        List<MovieInfo> movies   = movieInfoService.getAllMovieInfo();
        List<MovieInfo> filtered = new ArrayList(); // ughhhh the memory inefficiencies!!!
        for (MovieInfo info : movies) {
            boolean include = true;
            if (!(searchParams.title == null || searchParams.title.equals(""))) {
                if (!info.getTitle().toUpperCase().contains(searchParams.title.toUpperCase()))
                    include = false;
            }
            if (!(searchParams.cat == null || searchParams.cat.equals("") || searchParams.cat.equals("Any"))) {
                if (!info.getCategory().equalsIgnoreCase(searchParams.cat))
                    include = false;
            }
            if (!(searchParams.status == null || searchParams.status.equals("") || searchParams.status.equals("any"))) {
                if (false) // TODO: Determine showing status
                    include = false;
            }
            
            if (include)
                filtered.add(info);
        }
        model.addAttribute("movies", filtered);
        return "search";
    }

}