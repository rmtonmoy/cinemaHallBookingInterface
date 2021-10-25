package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void saveCustomer(Customer customer) {
        this.customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(long id) {
        Optional<Customer> optional = customerRepository.findById(id);
        Customer customer = null;
        if (optional.isPresent()) {
            customer = optional.get();
        } else {
            throw new RuntimeException("Customer not found for id: " + id);
        }
        return customer;
    }

    @Override 
    public void deleteCustomerById(long id) {
        this.customerRepository.deleteById(id);
    }
}
