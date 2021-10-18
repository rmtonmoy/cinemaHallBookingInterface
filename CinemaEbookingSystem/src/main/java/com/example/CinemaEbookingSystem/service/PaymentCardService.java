package com.example.CinemaEbookingSystem.service;
import com.example.CinemaEbookingSystem.model.PaymentCard;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PaymentCardService {
    List<PaymentCard> getAllCard();
    void SavepaymentCard(PaymentCard paymentCard);
}
