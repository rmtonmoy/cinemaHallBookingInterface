package com.example.CinemaEbookingSystem.service;

import java.util.Base64;
import java.util.List;

import com.example.CinemaEbookingSystem.dto.PaymentCardDto;
import com.example.CinemaEbookingSystem.dto.UserRegistrationDto;
import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.model.Mail;
import com.example.CinemaEbookingSystem.model.PaymentCard;
import com.example.CinemaEbookingSystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PaymentCardService paymentCardService;

    @Autowired
    private EmailService emailService;

    public CustomerServiceImpl(CustomerRepository customerRepository, PaymentCardService paymentCardService, EmailService emailService) {
        super();
        this.customerRepository = customerRepository;
        this.paymentCardService = paymentCardService;
        this.emailService = emailService;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public boolean save(UserRegistrationDto userRegistrationDto, PaymentCardDto paymentCards) {

        String CustomerEmailAddress = userRegistrationDto.getEmail();
        Customer foundCustomer = customerRepository.findByEmail(CustomerEmailAddress);
        if(foundCustomer == null) {
            Customer customer = new Customer(userRegistrationDto.getFirstName(), userRegistrationDto.getLastName(),
                    Base64.getEncoder().encodeToString(userRegistrationDto.getPassword().getBytes()), CustomerEmailAddress,
                    userRegistrationDto.getDob(), userRegistrationDto.getStatus(), userRegistrationDto.isIsRegistered());

            Customer customer2 = customerRepository.save(customer);
            for (PaymentCard paymentCard : paymentCards.getCards()) {
                if(paymentCard.getCardHolder() != null)
                {
                    paymentCardService.save(paymentCard,customer2);
                }
            }
            emailService.sendEmail(CustomerEmailAddress);
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
