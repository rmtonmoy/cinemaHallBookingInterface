package com.example.CinemaEbookingSystem.service;

import java.util.Collection;
import java.util.List;

import com.example.CinemaEbookingSystem.dto.UserRegistrationDto;
import com.example.CinemaEbookingSystem.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface CustomerService {
    List<Customer> getAllCustomers();

    boolean save(UserRegistrationDto userRegistrationDto);

}
