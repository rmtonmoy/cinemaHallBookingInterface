package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.repository.PaymentCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.CinemaEbookingSystem.model.PaymentCard;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentCardServiceImpl implements PaymentCardService {

    @Autowired
    private PaymentCardRepository paymentCardRepository;

    @Override
    public List<PaymentCard> getAllCard(){
        return paymentCardRepository.findAll();
    }

    @Override
    public void savePaymentCard(PaymentCard paymentCard) {
        this.paymentCardRepository.save(paymentCard);
    }

}
