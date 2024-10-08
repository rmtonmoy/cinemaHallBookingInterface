package com.example.CinemaEbookingSystem.controller;

import javax.servlet.http.HttpSession;
import com.example.CinemaEbookingSystem.model.PaymentCard;
import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.repository.CustomerRepository;
import com.example.CinemaEbookingSystem.service.CustomerService;
import com.example.CinemaEbookingSystem.service.PaymentCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class PaymentCardController {

    @Autowired
    private PaymentCardService paymentCardService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping(path = "/editPaymentInfo")
    public String editPaymentInfo(Model model, HttpSession session){
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("userName", session.getAttribute("name"));
        
        long customerID = customerRepository.findCustomerId(session.getAttribute("email").toString());
        Customer customer = customerService.getCustomerById(customerID);
        List<PaymentCard> cardList = paymentCardService.encodePaymentCards(customer);

        // Display list of payment cards
        model.addAttribute("listCards", cardList);
        return "Edit-Payment-Info";
    }

    @GetMapping(path = "/addPaymentCardForm")
    public String addPaymentCardForm(Model model, HttpSession session) {
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("userName", session.getAttribute("name"));

        long customerID = customerRepository.findCustomerId(session.getAttribute("email").toString());
        Customer customer = customerService.getCustomerById(customerID);
        List<PaymentCard> cardList = customer.getCardlist();

        if (cardList.size() >= 3) {
            return "redirect:/editPaymentInfo?LimitExceeded";
        } else {
            // Create model attribute to bind form data
            PaymentCard paymentCard = new PaymentCard();
            model.addAttribute("paymentCard", paymentCard);
            return "New-Payment-Card";
        }
    }

    @PostMapping(path = "/savePaymentCard")
    public String savePaymentCard(@ModelAttribute("paymentCard") PaymentCard paymentCard, HttpSession session) {
        Customer customer = customerRepository.findByEmail(session.getAttribute("email").toString());
        
        // Save payment card to database
        paymentCardService.updatePaymentCard(paymentCard, customer);
        return "redirect:/editPaymentInfo";
    }
    
    @GetMapping(path = "/updatePaymentCardForm/{id}")
    public String updatePaymentCardForm(@PathVariable(value = "id") long id, Model model, HttpSession session) {
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("userName", session.getAttribute("name"));

        // Get payment card from the service
        PaymentCard paymentCard = paymentCardService.getPaymentCardById(id);
        paymentCardService.decodePaymentCard(paymentCard);
        
        // Set payment card as a model attribute to pre-populate the form
        model.addAttribute("paymentCard", paymentCard);
        return "Update-Payment-Card";
    }

    @GetMapping(path = "/deletePaymentCard/{id}")
    public String deletePaymentCard(@PathVariable(value = "id") long id) {
        
        // Call delete payment card method
        this.paymentCardService.deletePaymentCardById(id);
        return "redirect:/editPaymentInfo";
    }
    
}
