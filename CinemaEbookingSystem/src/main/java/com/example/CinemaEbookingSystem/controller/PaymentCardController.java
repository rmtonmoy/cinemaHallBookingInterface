package com.example.CinemaEbookingSystem.controller;

import javax.servlet.http.HttpSession;

import com.example.CinemaEbookingSystem.model.PaymentCard;
import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.repository.CustomerRepository;
import com.example.CinemaEbookingSystem.repository.PaymentCardRepository;
import com.example.CinemaEbookingSystem.service.PaymentCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class PaymentCardController {

    @Autowired
    private PaymentCardService paymentCardService;

    @Autowired
    private PaymentCardRepository paymentCardRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping(path = "/editPaymentInfo")
    public String editPaymentInfo(Model model, HttpSession session){
        long customerID = customerRepository.findCustomerId(session.getAttribute("email"));

        // Display list of payment cards
        model.addAttribute("listCards", paymentCardRepository.findPaymentCardsById(customerID));
        return "Edit-Payment-Info";
    }

    @GetMapping(path = "/addPaymentCardForm")
    public String addPaymentCardForm(Model model) {

        // Create model attribute to bind form data
        PaymentCard paymentCard = new PaymentCard();
        model.addAttribute("paymentCard", paymentCard);
        return "New-Payment-Card";
    }

    @PostMapping(path = "/savePaymentCard")
    public String savePaymentCard(@ModelAttribute("paymentCard") PaymentCard paymentCard) {

        // Save payment card to database
        paymentCardService.savePaymentCard(paymentCard);
        return "redirect:/editPaymentInfo";
    }
    
    @GetMapping(path = "/updatePaymentCardForm/{id}")
    public String updatePaymentCardForm(@PathVariable(value = "id") long id, Model model) {
        
        // Get payment card from the service
        PaymentCard paymentCard = paymentCardService.getPaymentCardById(id);
        
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
