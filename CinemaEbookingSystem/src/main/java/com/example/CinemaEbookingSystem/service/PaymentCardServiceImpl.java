package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.dto.PaymentCardDto;
import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.repository.PaymentCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.CinemaEbookingSystem.model.PaymentCard;
import org.springframework.stereotype.Service;

import java.util.Base64;
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
    public void save(PaymentCard paymentCard, Customer customer) {
        paymentCard.setCustomer(customer); // safety; needed for signup form
        this.paymentCardRepository.save(paymentCard);
    }

    @Override
    public void updatePaymentCard(PaymentCard paymentCard, Customer customer) {
        paymentCard.assignToCustomer(customer);
        paymentCard.setCardNumber(Base64.getEncoder().encodeToString(paymentCard.getCardNumber().getBytes()));
        paymentCardRepository.save(paymentCard);
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

    @Override
    public List<PaymentCard> encodePaymentCards(Customer customer) {
        List<PaymentCard> cardList = customer.getCardlist();
        
        for (int i = 0; i < cardList.size(); i++) {
            PaymentCard paymentCard = cardList.get(i);
            String cardNumber = paymentCard.getCardNumber();

            if (!(paymentCard.isEncoded(cardNumber))) {
                paymentCard.setCardNumber(paymentCard.encodeCardNumber(cardNumber));
            }
        }

        return cardList;
    }

    @Override
    public void decodePaymentCard(PaymentCard paymentCard) {
        String cardNumber = paymentCard.getCardNumber();
        paymentCard.setCardNumber(paymentCard.decodeCardNumber(cardNumber));
    }
}
