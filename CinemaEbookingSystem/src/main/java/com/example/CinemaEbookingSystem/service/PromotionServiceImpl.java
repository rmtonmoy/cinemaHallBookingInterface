package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.CinemaEbookingSystem.model.Promotion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionServiceImpl {

    EmailService emailService;

    public void sendPromoEmail(String toMail, Promotion promotion) {
        emailService.sendPromotionalEmail(toMail, promotion);
    }

    public void discontinuePromo(Promotion promotion) {
        promotion.setPromoDescription("Sorry, this promotions is no longer running.");
        promotion.setActive(false);
    }

}
