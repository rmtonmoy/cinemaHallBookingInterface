// Checkout page controller by James Hyun
// sorta dunno what i'm doing here but hey as long as it works B)

package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.dto.BookingDto;
import com.example.CinemaEbookingSystem.dto.CheckoutDto;
import com.example.CinemaEbookingSystem.model.*;
import com.example.CinemaEbookingSystem.repository.CustomerRepository;
import com.example.CinemaEbookingSystem.repository.TicketPriceRepository;
import com.example.CinemaEbookingSystem.repository.TicketRepository;
import com.example.CinemaEbookingSystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CheckoutController {
    @Autowired
    MovieInfoService movieInfoService;
    
    @Autowired
    OneShowService oneShowService;

    @Autowired
    PromotionService promotionService;
    
    @Autowired
    private PaymentCardService paymentCardService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private TicketService ticketService;

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
        model.addAttribute("booking", new BookingDto());

        model.addAttribute("something", "Cinema E-booking System");
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("userName", session.getAttribute("name"));
        if(session.getAttribute("email")==null)
        {
            return "redirect:/signin";
        }
        return "book";
    }
    
    @PostMapping(path = "/book")
    String postBook(Model model, HttpSession session, @ModelAttribute("booking") BookingDto bookingDto) {
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("userName", session.getAttribute("name"));
        model.addAttribute("booking", new BookingDto());
        model.addAttribute("movies", movieInfoService.listOfCurrentMovies());
        model.addAttribute("OSS", oneShowService);
        
        System.out.println(bookingDto.showingId);
        System.out.println(bookingDto.ticketsParam);
        
        if (bookingDto.ticketsParam == null || bookingDto.ticketsParam.equals("")) {
            return "redirect:/book";
        }
        List<Ticket> tickets = bookingDto.getTickets(ticketService);
        
        try {
            boolean okay = true;
            List<Ticket> problematic = new ArrayList<>();
            for (Ticket ticket : tickets) {
                if (!ticketService.canPurchaseTicket(ticket.getId())) {
                    okay = false;
                    problematic.add(ticket);
                }
            }
            if (!okay) {
                model.addAttribute("problematic", problematic);
                return "book";
            } else {
                Customer customer = customerService.getCustomerByEmail((String) session.getAttribute("email"));
                for (Ticket ticket : tickets) {
                    ticketService.bookTicket(ticket.getId(), customer.getId(), ticket.getTicketType());
                }
                return "redirect:/viewCart";
            }
        } catch(Exception e) {
            model.addAttribute("error", true);
            return "book";
        }
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
        session.setAttribute("total", cartTotal.getOrderTotal());
        return "viewCart";
    }


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
        session.setAttribute("total", orderTotal);
        return "viewCart";
    }

    @GetMapping(path = "/checkout")
    public String checkout( Model model, HttpSession session){

        PaymentCard paymentCard = new PaymentCard();
        float total = (float) session.getAttribute("total");
        float orderTotal = total;
        model.addAttribute("userName", session.getAttribute("name"));
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("orderTotal", orderTotal);
        model.addAttribute("paymentCard",paymentCard);
        model.addAttribute("total",session.getAttribute("total"));


        long customerID = customerRepository.findCustomerId(session.getAttribute("email").toString());
        Customer customer = customerService.getCustomerById(customerID);
        List<PaymentCard> cardList = paymentCardService.encodePaymentCards(customer);

        // Display list of payment cards
        model.addAttribute("listCards", cardList);

        return "checkout";
    }

    @ModelAttribute("checkoutDto")
    public CheckoutDto getCheckoutDto(){ return new CheckoutDto();}

    @GetMapping(path= "applyPromo")
    public String applyPromo(Model model, HttpSession session){

        float total = (float) session.getAttribute("total");
        float orderTotal = total;
        model.addAttribute("userName", session.getAttribute("name"));
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("orderTotal", orderTotal);

        return "applyPromo";
    }

    @PostMapping(path = "/promoApplied")
    public String promoApplied(Model model, @ModelAttribute("checkoutDto") CheckoutDto checkoutDto, HttpSession session){

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

        int discountRate = promotionService.getDiscountRate(checkoutDto.getPromoCode());

        if(discountRate == -1){
            session.setAttribute("total",cartTotal.getDiscountedTotal());
            return "redirect:/applyPromo?InvalidPromo";
        }
        else {
            cartTotal.setDiscountedTotal((float) (cartTotal.getOrderTotal() * (1.0 - discountRate/100.0)));
            session.setAttribute("total",cartTotal.getDiscountedTotal());
        }
        return "redirect:/checkout";
    }

    @PostMapping(path = "/paymentConfirmation")
    public String postCheckout(@ModelAttribute("checkoutDto") CheckoutDto checkoutDto, HttpSession session){
        System.out.println("HEY!!! CARD ID NUMBER IS " + checkoutDto.getPaymentCardId() + ". Does it look good?");
        String email = (String) session.getAttribute("email");
        Customer customer = customerRepository.findByEmail(email);
        System.out.print("BOUGHT BY CUSTOMER " + customer.getId());
        ticketService.confirmPurchase(customer.getId(), Integer.valueOf(checkoutDto.getPaymentCardId()), (float)session.getAttribute("total"));
        return "redirect:/paymentConfirmation";
    }


    @GetMapping(path = "/paymentConfirmation")
    public String showPaymentConfirmation(Model model, HttpSession session){

        model.addAttribute("userName", session.getAttribute("name"));
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("something", "Cinema E-booking System");
        return "paymentConfirmation";
    }
}