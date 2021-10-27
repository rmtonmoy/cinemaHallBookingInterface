package com.example.CinemaEbookingSystem.controller;
import com.example.CinemaEbookingSystem.service.PaymentCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentCardController {

    @Autowired
    private PaymentCardService paymentCardService;

    @GetMapping(path = "/dummy")
    public String dummyPage(Model model){
        model.addAttribute("listCards", paymentCardService.getAllCard());
        return "index";
    }

}
