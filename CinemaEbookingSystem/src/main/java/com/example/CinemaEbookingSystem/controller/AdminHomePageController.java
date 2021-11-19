package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.dto.PromotionDto;
import com.example.CinemaEbookingSystem.dto.TheaterDto;
import com.example.CinemaEbookingSystem.model.MovieInfo;
import com.example.CinemaEbookingSystem.model.OneShow;
import com.example.CinemaEbookingSystem.model.Theater;
import com.example.CinemaEbookingSystem.repository.OneShowRepository;
import com.example.CinemaEbookingSystem.service.*;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminHomePageController {
    @Autowired
    PopulateDBService populateDBService;

    @Autowired
    MovieInfoService movieInfoService;

    @Autowired
    OneShowService oneShowService;

    @Autowired
    TicketService ticketService;

    @Autowired
    TheaterService theaterService;

    @ModelAttribute("theaterDto")
    public TheaterDto theaterDto(){ return new TheaterDto();}

    @GetMapping(path = "/addTheater")
    public String showAllTheaters(Model model){
        List<Theater> theaterList = theaterService.getAllTheaters();
        model.addAttribute("listTheater", theaterList);
        return "addTheater";
    }

    @PostMapping(path = "/addTheater")
    public String addTheater(@ModelAttribute("theaterDto") TheaterDto theaterDto){
        theaterService.addTheater(theaterDto.getMaxR(), theaterDto.getMaxC());
        return "redirect:/addTheater";
    }

    @RequestMapping(value = "assignMovie", method = RequestMethod.GET)
    void assignMovie(Model model, @RequestParam("date") String date, @RequestParam("theaterId") long theaterId,
                     @RequestParam("startingAt") int startingAt, @RequestParam("movieName") String movieName)
    {
        if(movieInfoService.hasMovie(movieName)){
            OneShow oneShow = oneShowService.save(date, theaterId, startingAt, movieName);
            boolean canAdd = ticketService.addTickets(oneShow);

            if(canAdd){
                model.addAttribute("verdict", "Tickets added!");
            }
            else{
                model.addAttribute("verdict", "Time Conflict!");
            }
        }
        else{
            model.addAttribute("verdict", "Movie Not Found!");
        }
    }

    @GetMapping(path = "/adminHome")
    String adminHome(Model model, HttpSession session){
        System.out.println(session.getAttribute("email"));
        System.out.println(session.getAttribute("name"));
        model.addAttribute("something", "Cinema E-booking System");
        model.addAttribute("userName", session.getAttribute("name"));
        model.addAttribute("email", session.getAttribute("email"));
        return "adminHome";
    }

    @GetMapping(path = "/manageMovies")
    String manageMovies(Model model, HttpSession session){
        model.addAttribute("something", "Cinema E-booking System");
        return "manageMovies";
    }


}
