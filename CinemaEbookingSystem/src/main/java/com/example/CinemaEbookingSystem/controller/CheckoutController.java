// Checkout page controller by James Hyun
// sorta dunno what i'm doing here but hey as long as it works B)

package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.model.*;
import com.example.CinemaEbookingSystem.repository.CustomerRepository;
import com.example.CinemaEbookingSystem.repository.TicketPriceRepository;
import com.example.CinemaEbookingSystem.repository.TicketRepository;
import com.example.CinemaEbookingSystem.service.CustomerService;
import com.example.CinemaEbookingSystem.service.MovieInfoService;
import com.example.CinemaEbookingSystem.service.OneShowService;
import com.example.CinemaEbookingSystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import javax.servlet.http.HttpSession;

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

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TicketPriceRepository ticketPriceRepository;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping(path = "/viewCart")
    public String showAllCartItem(Model model, HttpSession session){
        model.addAttribute("something", "Cinema E-booking System");
        model.addAttribute("userName", session.getAttribute("name"));
        model.addAttribute("email", session.getAttribute("email"));

        Customer customer = customerRepository.findByEmail(session.getAttribute("email").toString());
        List<Ticket> tickets = ticketRepository.findByCID(customer.getId());

        List<CartItem> cartItems = new ArrayList<CartItem>();
        for (int i = 0; i < tickets.size(); i++) {
            Ticket ticket = tickets.get(i);
            System.out.println("lallalalala   " + ticketService.getTicketPriceByType(ticket.getTicketType().toString()));
            cartItems.add(new CartItem(ticket.getId(),ticket.getCustomerId(),
                    ticket.getShow(),ticket.getTypeOfTicket(),ticketService.getTicketPriceByType(ticket.getTicketType().toString())));
        }
        model.addAttribute("CartItem", cartItems);
        return "viewCart";
    }
    @Autowired
    TicketService ticketService;

    @GetMapping(path = "/performDeleteFromCartOperation/{id}")
    public String performDeleteFromCartOperation(@PathVariable(value = "id") long id, Model model, HttpSession session){

        model.addAttribute("userName", session.getAttribute("name"));
        model.addAttribute("email", session.getAttribute("email"));
        ticketService.deleteFromCart(id);
        return "viewCart";
    }
}