package com.example.CinemaEbookingSystem.service;
 
import com.example.CinemaEbookingSystem.dto.PaymentCardDto;
import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.model.PaymentCard;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PaymentCardService {
    List<PaymentCard> getAllCards();
    void save(PaymentCard paymentCard, Customer customer);
    void updatePaymentCard(PaymentCard paymentCard, Customer customer);
    PaymentCard getPaymentCardById(long id);
    void deletePaymentCardById(long id);
    List<PaymentCard> encodePaymentCards(Customer customer);
    void decodePaymentCard(PaymentCard paymentCard);
}
