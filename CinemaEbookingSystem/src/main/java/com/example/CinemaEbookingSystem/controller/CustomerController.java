package com.example.CinemaEbookingSystem.controller;

import javax.servlet.http.HttpSession;

import com.example.CinemaEbookingSystem.dto.PasswordDto;
import com.example.CinemaEbookingSystem.model.*;
import com.example.CinemaEbookingSystem.repository.BookingRepository;
import com.example.CinemaEbookingSystem.repository.CustomerRepository;
import com.example.CinemaEbookingSystem.repository.TicketRepository;
import com.example.CinemaEbookingSystem.service.CustomerService;
import com.example.CinemaEbookingSystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private BookingRepository bookingRepository;

    @PostMapping(path = "/saveCustomerInfo")
    public String saveCustomerInfo(@ModelAttribute("customer") Customer customer) {

        // Save customer to database
        customerService.saveCustomer(customer);

        // Return to Edit-Profile.html 
        return "redirect:/editProfile?SuccessInfo";
    }

    @PostMapping(path = "/editProfile/changePassword")
    public String changePassword(@ModelAttribute("passwordDto") PasswordDto passwordDto, HttpSession session) {
        long customerID = customerRepository.findCustomerId(session.getAttribute("email").toString());

        if (!(customerService.isCorrectPassword(customerID, passwordDto))) {
            return "redirect:/editProfile/changePassword?IncorrectPassword";
        } else {
            return "redirect:/editProfile?SuccessPassword";
        }
    }

    @GetMapping(path = "/editProfile")
    public String editProfile(Model model, HttpSession session) {
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("userName", session.getAttribute("name"));

        long customerID = customerRepository.findCustomerId(session.getAttribute("email").toString());

        // Get customer from the service
        Customer customer = customerService.getCustomerById(customerID);

        // Set customer as a model attribute to pre-populate the form
        model.addAttribute("customer", customer);
        return "Edit-Profile";
    }

    @GetMapping(path = "/editProfile/changePassword")
    public String changePassword(Model model, HttpSession session) {
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("userName", session.getAttribute("name"));
        model.addAttribute("passwordDto", new PasswordDto());

        return "changePassword";
    }

    @GetMapping(path = "/orderHistory")
    public String editPaymentInfo(Model model, HttpSession session) {
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("userName", session.getAttribute("name"));

        long customerID = customerRepository.findCustomerId(session.getAttribute("email").toString());
        Customer customer = customerService.getCustomerById(customerID);
        List<Booking> bookings = customer.getBooking();

        model.addAttribute("bookings", bookings);
        return "Order-History";
    }

    @GetMapping(path = "/showTickets/{id}")
    public String showTickets(@PathVariable(value = "id") long id, Model model, HttpSession session) {

        model.addAttribute("something", "Cinema E-booking System");
        model.addAttribute("userName", session.getAttribute("name"));
        model.addAttribute("email", session.getAttribute("email"));

        Customer customer = customerRepository.findByEmail(session.getAttribute("email").toString());
        List<Booking> bookingList = bookingRepository.findAll();

        Booking booking = null;
        for(Booking anotherBooking : bookingList){
            if(anotherBooking.getId() == id){
                booking = anotherBooking;
                break;
            }
        }
        List<Ticket> tickets = booking.getTicketList();

        List<CartItem> cartItems = new ArrayList<CartItem>();
        float subtotal=0, bookingFee=0, salesTax=0, orderTotal=0; int i;
        CartItem cartItem;

        for (i = 0; i < tickets.size(); i++) {
            Ticket ticket = tickets.get(i);
            if(ticket.isInCart() == true){
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
        return "viewTickets";
    }
}