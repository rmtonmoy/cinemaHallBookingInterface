// Checkout page controller by James Hyun
// sorta dunno what i'm doing here but hey as long as it works B)

package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.model.CartItem;
import com.example.CinemaEbookingSystem.model.CartTotal;
import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.model.MovieInfo;
import com.example.CinemaEbookingSystem.model.PaymentCard;
import com.example.CinemaEbookingSystem.model.Theater;
import com.example.CinemaEbookingSystem.model.TheaterStats;
import com.example.CinemaEbookingSystem.model.Ticket;
import com.example.CinemaEbookingSystem.repository.CustomerRepository;
import com.example.CinemaEbookingSystem.repository.TicketPriceRepository;
import com.example.CinemaEbookingSystem.repository.TicketRepository;
import com.example.CinemaEbookingSystem.service.CustomerService;
import com.example.CinemaEbookingSystem.service.MovieInfoService;
import com.example.CinemaEbookingSystem.service.OneShowService;
import com.example.CinemaEbookingSystem.service.PaymentCardService;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckoutController {
    @Autowired
    MovieInfoService movieInfoService;
    
    @Autowired
    OneShowService oneShowService;
    
    @Autowired
    private PaymentCardService paymentCardService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

//    @GetMapping(path = "/checkout")
//    String getCheckout(Model model, HttpSession session) {
//        model.addAttribute("subtotal", 0.00);
//        model.addAttribute("tax", 0.00);
//        model.addAttribute("total", 0.00);
//
//        model.addAttribute("email", session.getAttribute("email"));
//        model.addAttribute("userName", session.getAttribute("name"));
//        return "checkout";
//    }
    
    @GetMapping(path = "/book")
    String getBook(Model model, HttpSession session) {
        List<MovieInfo> movies = movieInfoService.listOfCurrentMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("OSS", oneShowService);

        model.addAttribute("something", "Cinema E-booking System");
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("userName", session.getAttribute("name"));
        if(session.getAttribute("email")==null)
        {
            return "redirect:/signin";
        }
        return "book";
    }

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TicketPriceRepository ticketPriceRepository;


    @GetMapping(path = "/viewCart")
    public String showAllCartItem(Model model, HttpSession session){
        model.addAttribute("something", "Cinema E-booking System");
        model.addAttribute("userName", session.getAttribute("name"));
        model.addAttribute("email", session.getAttribute("email"));

        Customer customer = customerRepository.findByEmail(session.getAttribute("email").toString());
        List<Ticket> tickets = ticketRepository.findByCID(customer.getId());

        List<CartItem> cartItems = new ArrayList<CartItem>();
        float subtotal=0, bookingFee=0, salesTax=0, orderTotal=0; int i;
        CartItem cartItem;

        for (i = 0; i < tickets.size(); i++) {
            Ticket ticket = tickets.get(i);
            if(ticket.isInCart() == false){
                continue;
            }

            //System.out.println("cart test   " + ticketService.getTicketPriceByType(ticket.getTicketType().toString()));
            cartItem =new CartItem(ticket.getId(),ticket.getCustomerId(),
                    ticket.getShow(),ticket.getTypeOfTicket(),ticketService.getTicketPriceByType(ticket.getTicketType().toString()));
            cartItems.add(cartItem);
            subtotal = subtotal + Float.parseFloat(cartItem.getPrice());
        }

        CartTotal cartTotal;
        bookingFee = Float.parseFloat(ticketService.getBookingFee());
        salesTax = subtotal * 4 / 100;
        orderTotal = Float.parseFloat(String.format("%.2f", subtotal + bookingFee + salesTax));
        cartTotal = new CartTotal(subtotal, salesTax, bookingFee, orderTotal);

        model.addAttribute("CartItem", cartItems);
        model.addAttribute("CartTotal", cartTotal);
        return "viewCart";
    }
    @Autowired
    TicketService ticketService;

    @GetMapping(path = "/performDeleteFromCartOperation/{id}")
    public String performDeleteFromCartOperation(@PathVariable(value = "id") long id, Model model, HttpSession session){

        model.addAttribute("userName", session.getAttribute("name"));
        model.addAttribute("email", session.getAttribute("email"));

        ticketService.deleteFromCart(id);
        Customer customer = customerRepository.findByEmail(session.getAttribute("email").toString());
        List<Ticket> tickets = ticketRepository.findByCID(customer.getId());

        List<CartItem> cartItems = new ArrayList<CartItem>();
        float subtotal=0, bookingFee=0, salesTax=0, orderTotal=0; int i;
        CartItem cartItem;

        for (i = 0; i < tickets.size(); i++) {
            Ticket ticket = tickets.get(i);
            if(ticket.isInCart() == false){
                continue;
            }
            //System.out.println("cart test   " + ticketService.getTicketPriceByType(ticket.getTicketType().toString()));
            cartItem =new CartItem(ticket.getId(),ticket.getCustomerId(),
                    ticket.getShow(),ticket.getTypeOfTicket(),ticketService.getTicketPriceByType(ticket.getTicketType().toString()));
            cartItems.add(cartItem);
            subtotal = subtotal + Float.parseFloat(cartItem.getPrice());
        }

        CartTotal cartTotal;
        if(i==0)
            cartTotal = new CartTotal();
        else {
            bookingFee = Float.parseFloat(ticketService.getBookingFee());
            salesTax = subtotal * 4 / 100;
            orderTotal = Float.parseFloat(String.format("%.2f", subtotal + bookingFee + salesTax));
            cartTotal = new CartTotal(subtotal, salesTax, bookingFee, orderTotal);
        }
        model.addAttribute("CartItem", cartItems);
        model.addAttribute("CartTotal", cartTotal);
        return "viewCart";
    }

    @GetMapping(path = "/checkout/{total}")
    public String checkout(@PathVariable(value = "total") String total, Model model, HttpSession session){

        PaymentCard paymentCard = new PaymentCard();
        float orderTotal = Float.parseFloat(total)/100;
        model.addAttribute("userName", session.getAttribute("name"));
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("orderTotal", orderTotal);
        model.addAttribute("paymentCard",paymentCard);


        long customerID = customerRepository.findCustomerId(session.getAttribute("email").toString());
        Customer customer = customerService.getCustomerById(customerID);
        List<PaymentCard> cardList = paymentCardService.encodePaymentCards(customer);

        // Display list of payment cards
        model.addAttribute("listCards", cardList);

        return "checkout";
    }
}