package com.example.CinemaEbookingSystem.service;

import java.util.Collection;
import java.util.List;

import com.example.CinemaEbookingSystem.dto.UserRegistrationDto;
import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.repository.CustomerRepository;
import org.springframework.data.jpa.repository.Query;
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
    public boolean save(UserRegistrationDto userRegistrationDto) {

        Collection customerCollection = customerRepository.findAllByEmail(userRegistrationDto.getEmail());
        if(customerCollection.isEmpty()) {
            Customer customer = new Customer(userRegistrationDto.getFirstName(), userRegistrationDto.getLastName(),
                    userRegistrationDto.getPassword(), userRegistrationDto.getEmail(), userRegistrationDto.getDob());
            customerRepository.save(customer);
            return true;
        }
        else
            return false;

    }

}
