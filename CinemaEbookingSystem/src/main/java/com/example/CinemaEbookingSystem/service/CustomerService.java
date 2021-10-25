package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomerService {
    List<Customer> getAllCustomers();
    void saveCustomer(Customer customer);
    Customer getCustomerById(long id);
    void deleteCustomerById(long id);
}
