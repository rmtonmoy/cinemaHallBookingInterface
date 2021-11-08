// Checkout page controller by James Hyun
// sorta dunno what i'm doing here but hey as long as it works B)

package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.model.MovieInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Arrays;
import javax.servlet.http.HttpSession;

@Controller
public class CheckoutController {
    
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
        List<MovieInfo> movies = Arrays.asList( // TODO: Read from some external file for the list of movies
            new MovieInfo("Batman"),
            new MovieInfo("Dune"),
            new MovieInfo("Venom"),
            new MovieInfo("Free Guy"),
            new MovieInfo("Shang-Chi")
        );
        model.addAttribute("movies", movies);
        
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("userName", session.getAttribute("name"));
        return "book";
    }
}