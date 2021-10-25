package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // For admin use
    @GetMapping(path = "")
    public String editCustomers(Model model) {

        // Display list of customers
        model.addAttribute("listCustomers", customerService.getAllCustomers());
        return "Edit-Customers";
    }

    @PostMapping(path = "/saveCustomerInfo")
    public String saveCustomerInfo(@ModelAttribute("customer") Customer customer) {

        // Save customer to database
        customerService.saveCustomer(customer);

        // Return to Edit-Profile.html and display customer with id 1
        return "redirect:/editProfile/1";
    }
    
    @GetMapping(path = "/editProfile/{id}")
    public String editProfile(@PathVariable(value = "id") long id, Model model) {
        
        // Get customer from the service
        Customer customer = customerService.getCustomerById(id);
        
        // Set customer as a model attribute to pre-populate the form
        model.addAttribute("customer", customer);
        return "Edit-Profile";
    }
}