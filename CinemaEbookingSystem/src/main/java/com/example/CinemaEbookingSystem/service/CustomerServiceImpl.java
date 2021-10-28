package com.example.CinemaEbookingSystem.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

import com.example.CinemaEbookingSystem.dto.UserRegistrationDto;
import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.repository.CustomerRepository;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import java.util.Base64;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        super();
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public boolean save(UserRegistrationDto userRegistrationDto) {

        Customer foundCustomer = customerRepository.findByEmail(userRegistrationDto.getEmail());
        if(foundCustomer == null) {
            Customer customer = new Customer(userRegistrationDto.getFirstName(), userRegistrationDto.getLastName(),
                    Base64.getEncoder().encodeToString(userRegistrationDto.getPassword().getBytes()), userRegistrationDto.getEmail(), userRegistrationDto.getDob(), userRegistrationDto.getStatus());
            customerRepository.save(customer);
            return true;
        }
        else
            return false;

    }

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
