package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.dto.PaymentCardDto;
import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.repository.PaymentCardRepository;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.model.PaymentCard;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentCardServiceImpl implements PaymentCardService {

    @Autowired
    private PaymentCardRepository paymentCardRepository;

    @Autowired
    private CustomerService customerService;

    @Override
    public List<PaymentCard> getAllCards(){
        return paymentCardRepository.findAll();
    }

    @Override
    public void savePaymentCard(PaymentCard paymentCard, long customerID) {
        Customer customer = customerService.getCustomerById(customerID);
        paymentCard.assignToCustomer(customer);
        paymentCard.setCardNumber(Base64.getEncoder().encodeToString(paymentCard.getCardNumber().getBytes()));
        paymentCardRepository.save(paymentCard);
    }

    @Override
    public void save(PaymentCardDto paymentCardDto, Customer customer) {
        PaymentCard paymentCard = new PaymentCard(paymentCardDto.getCardNumber(),paymentCardDto.getSecurityCode(),
                paymentCardDto.getCardHolder(),paymentCardDto.getCardType(), paymentCardDto.getExpirationDate(),
                paymentCardDto.getBillingAddress(), customer);
        this.paymentCardRepository.save(paymentCard);
    }

    @Override
    public PaymentCard getPaymentCardById(long id) {
        Optional<PaymentCard> optional = paymentCardRepository.findById(id);
        PaymentCard paymentCard = null;
        if (optional.isPresent()) {
            paymentCard = optional.get();
        } else {
            throw new RuntimeException("Payment card not found for id: " + id);
        }
        return paymentCard;
    }

    @Override 
    public void deletePaymentCardById(long id) {
        this.paymentCardRepository.deleteById(id);
    }
}
