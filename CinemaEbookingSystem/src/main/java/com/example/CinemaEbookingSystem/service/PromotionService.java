package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.dto.PromotionDto;
import com.example.CinemaEbookingSystem.model.Promotion;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface PromotionService {

    public boolean sendPromoEmail(Promotion promotion);

    public void discontinuePromo(Promotion promotion);

    List<Promotion> getAllPromotions();

    List<Promotion> getUnsentPromotions();

    List<Promotion> getSentPromotions();

    void savePromotion(PromotionDto promotiondto);

    Promotion getPromoById(long id);

    void updatePromotion(Promotion promotion);
}