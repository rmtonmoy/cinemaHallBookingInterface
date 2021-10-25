package com.example.CinemaEbookingSystem.service;
 
import com.example.CinemaEbookingSystem.model.PaymentCard;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PaymentCardService {
    List<PaymentCard> getAllCards();
    void savePaymentCard(PaymentCard paymentCard);
    PaymentCard getPaymentCardById(long id);
    void deletePaymentCardById(long id);
}
