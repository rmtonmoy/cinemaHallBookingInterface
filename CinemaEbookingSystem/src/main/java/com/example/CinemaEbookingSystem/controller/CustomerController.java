package com.example.CinemaEbookingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

    @Autowired
    private com.example.CinemaEbookingSystem.service.CustomerService customerService;

    // display list of customers
    @GetMapping
    public String viewHomePage(Model model) {
        model.addAttribute("listCustomers", customerService.getAllCustomers());
        return "index";
    }
}