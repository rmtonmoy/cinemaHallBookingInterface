package com.example.CinemaEbookingSystem.service;

import java.util.List;

import com.example.CinemaEbookingSystem.dto.UserRegistrationDto;
import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.model.User;
import com.example.CinemaEbookingSystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Customer save(UserRegistrationDto userRegistrationDto) {
        Customer customer = new Customer(userRegistrationDto.getFirstName(), userRegistrationDto.getLastName(),
                userRegistrationDto.getPassword(), userRegistrationDto.getEmail(), userRegistrationDto.getDob());
        return customerRepository.save(customer);
    }


}
