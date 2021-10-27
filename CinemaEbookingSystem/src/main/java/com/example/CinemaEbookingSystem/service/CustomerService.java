package com.example.CinemaEbookingSystem.service;

import java.util.List;

import com.example.CinemaEbookingSystem.dto.UserRegistrationDto;
import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.model.User;
import org.springframework.stereotype.Component;

@Component
public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer save(UserRegistrationDto userRegistrationDto);
}
