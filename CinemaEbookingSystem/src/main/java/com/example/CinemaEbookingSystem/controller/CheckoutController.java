// Checkout page controller by James Hyun
// sorta dunno what i'm doing here but hey as long as it works B)

package com.example.CinemaEbookingSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Arrays;

@Controller
public class CheckoutController {
    
    @GetMapping(path = "/checkout")
    String getCheckout(Model model) {
        model.addAttribute("subtotal", 0.00);
        model.addAttribute("tax", 0.00);
        model.addAttribute("total", 0.00);
        return "checkout";
    }
    
    @GetMapping(path = "/book")
    String getBook(Model model) {
        List<Movie> movies = Arrays.asList( // TODO: Read from some external file for the list of movies
            new Movie("Batman"),
            new Movie("Dune"),
            new Movie("Venom"),
            new Movie("Free Guy"),
            new Movie("Shang-Chi")
        );
        model.addAttribute("movies", movies);
        return "book";
    }
}