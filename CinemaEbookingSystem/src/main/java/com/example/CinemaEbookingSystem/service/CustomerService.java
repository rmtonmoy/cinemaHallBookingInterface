package com.example.CinemaEbookingSystem.service;

import java.util.List;
import com.example.CinemaEbookingSystem.model.Customer;
import org.springframework.stereotype.Component;

@Component
public interface CustomerService {
    List<Customer> getAllCustomers();
}
