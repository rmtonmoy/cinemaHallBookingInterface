// Checkout page controller by James Hyun
// sorta dunno what i'm doing here but hey as long as it works B)

package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.model.MovieInfo;
import com.example.CinemaEbookingSystem.model.Theater;
import com.example.CinemaEbookingSystem.model.TheaterStats;
import com.example.CinemaEbookingSystem.model.Ticket;
import com.example.CinemaEbookingSystem.service.MovieInfoService;
import com.example.CinemaEbookingSystem.service.OneShowService;
import com.example.CinemaEbookingSystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Arrays;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckoutController {
    @Autowired
    MovieInfoService movieInfoService;
    
    @Autowired
    OneShowService oneShowService;
    
    @GetMapping(path = "/checkout")
    String getCheckout(Model model, HttpSession session) {
        model.addAttribute("subtotal", 0.00);
        model.addAttribute("tax", 0.00);
        model.addAttribute("total", 0.00);
        
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("userName", session.getAttribute("name"));
        return "checkout";
    }
    
    @GetMapping(path = "/book")
    String getBook(Model model, HttpSession session) {
        List<MovieInfo> movies = movieInfoService.listOfCurrentMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("OSS", oneShowService);
        
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("userName", session.getAttribute("name"));
        return "book";
    }
}