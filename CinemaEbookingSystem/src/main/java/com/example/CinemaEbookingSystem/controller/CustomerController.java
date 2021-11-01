package com.example.CinemaEbookingSystem.controller;

import javax.servlet.http.HttpSession;

import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.repository.CustomerRepository;
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

    @Autowired
    private CustomerRepository customerRepository;

    // For admin use
    @GetMapping(path = "")
    public String editCustomers(Model model, HttpSession session) {
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("userName", session.getAttribute("name"));

        // Display list of customers
        model.addAttribute("listCustomers", customerService.getAllCustomers());
        return "Edit-Customers";
    }

    @PostMapping(path = "/saveCustomerInfo")
    public String saveCustomerInfo(@ModelAttribute("customer") Customer customer) {
        // Save customer to database
        customerService.saveCustomer(customer);

        // Return to Edit-Profile.html 
        return "redirect:/editProfile";
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
}