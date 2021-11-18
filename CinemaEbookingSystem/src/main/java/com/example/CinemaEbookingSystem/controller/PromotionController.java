package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.service.EmailService;
import com.example.CinemaEbookingSystem.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class PromotionController {
    @Autowired
    private PromotionService promotionService;
    @Autowired
    private EmailService emailService;
/*
    @GetMapping(path = "/create_db")
    public String dummyPage(Model model){
        model.addAttribute("listCards", promotionService.getAllPromo());
        return "index";
    }
*/
}
