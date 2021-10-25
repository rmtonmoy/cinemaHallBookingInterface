package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.repository.PaymentCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.CinemaEbookingSystem.model.PaymentCard;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentCardServiceImpl implements PaymentCardService {

    @Autowired
    private PaymentCardRepository paymentCardRepository;

    @Override
    public List<PaymentCard> getAllCards(){
        return paymentCardRepository.findAll();
    }

    @Override
    public void savePaymentCard(PaymentCard paymentCard) {
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
