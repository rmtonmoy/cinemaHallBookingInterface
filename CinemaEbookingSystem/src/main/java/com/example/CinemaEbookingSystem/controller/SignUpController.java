package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.dto.UserRegistrationDto;
import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignUpController {

    private CustomerService customerService;

    public SignUpController(CustomerService customerService) {
        super();
        this.customerService = customerService;
    }

    @ModelAttribute("customer")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping(path = "/signup")
    public String showRegistrationForm() {
        return "signup";
    }

    @PostMapping(path = "/signup")
    public String registerUserAccount(@ModelAttribute("customer") UserRegistrationDto registrationDto) {
        System.out.print(registrationDto.getFirstName());
        customerService.save(registrationDto);
        return "redirect:/signup?success";
    }
}
