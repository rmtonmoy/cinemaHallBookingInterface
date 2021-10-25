package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.model.PaymentCard;
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

    @GetMapping(path = "/editPaymentInfo")
    public String editPaymentInfo(Model model){

        // Display list of payment cards
        model.addAttribute("listCards", paymentCardService.getAllCards());
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
