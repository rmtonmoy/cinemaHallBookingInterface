package com.example.CinemaEbookingSystem.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.example.CinemaEbookingSystem.dto.PasswordDto;
import com.example.CinemaEbookingSystem.dto.PaymentCardDto;
import com.example.CinemaEbookingSystem.dto.UserRegistrationDto;
import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.model.PaymentCard;
import com.example.CinemaEbookingSystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PaymentCardService paymentCardService;

    @Autowired
    private EmailService emailService;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public boolean save(UserRegistrationDto userRegistrationDto, PaymentCardDto paymentCards) {

        String CustomerEmailAddress = userRegistrationDto.getEmail();
        Customer foundCustomer = customerRepository.findByEmail(userRegistrationDto.getEmail());
        if(foundCustomer == null) {
            Customer customer = new Customer(userRegistrationDto.getFirstName(), userRegistrationDto.getLastName(),
                    Base64.getEncoder().encodeToString(userRegistrationDto.getPassword().getBytes()), userRegistrationDto.getEmail(),
                    userRegistrationDto.getDob(), userRegistrationDto.getStatus(), userRegistrationDto.isRegisteredForPromo());

            Customer customer2 = customerRepository.save(customer);
            for (PaymentCard paymentCard : paymentCards.getCards()) {
                if(paymentCard.getCardHolder() != null)
                {
                    paymentCardService.save(paymentCard,customer2);
                }
            }
            emailService.sendVerificationEmail(CustomerEmailAddress);
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

    @Override
    public boolean isCorrectPassword(long id, PasswordDto passwordDto) {
        Customer customer = getCustomerById(id);

        if (Base64.getEncoder().encodeToString(passwordDto.getOldPassword().getBytes()).equals(customer.getPassword())) {
            customer.setPassword(Base64.getEncoder().encodeToString(passwordDto.getNewPassword().getBytes()));
            return true;
        } else {
            return false;
        }
    }
}
