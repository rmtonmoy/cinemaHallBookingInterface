package com.example.CinemaEbookingSystem.service;

import java.util.Collection;
import java.util.List;

import com.example.CinemaEbookingSystem.dto.PaymentCardDto;
import com.example.CinemaEbookingSystem.dto.UserRegistrationDto;

import com.example.CinemaEbookingSystem.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomerService {
    List<Customer> getAllCustomers();
    boolean save(UserRegistrationDto userRegistrationDto, PaymentCardDto paymentCards);
    void saveCustomer(Customer customer);
    Customer getCustomerById(long id);
    void deleteCustomerById(long id);
}
