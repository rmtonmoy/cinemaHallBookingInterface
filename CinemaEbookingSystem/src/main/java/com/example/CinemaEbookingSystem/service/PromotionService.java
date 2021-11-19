package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.dto.PromotionDto;
import com.example.CinemaEbookingSystem.model.Promotion;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface PromotionService {

    public void sendPromoEmail(String toMail, Promotion promotion);
    public void discontinuePromo(Promotion promotion);

    List<Promotion> getAllPromotions();
    void savePromotion(PromotionDto promotiondto);

}