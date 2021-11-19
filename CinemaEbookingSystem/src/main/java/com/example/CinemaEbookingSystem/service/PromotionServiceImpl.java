package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.dto.PromotionDto;
import com.example.CinemaEbookingSystem.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.CinemaEbookingSystem.model.Promotion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {

    EmailService emailService;

    public void sendPromoEmail(String toMail, Promotion promotion) {
        emailService.sendPromotionalEmail(toMail, promotion);
    }

    public void discontinuePromo(Promotion promotion) {
        promotion.setPromoDescription("Sorry, this promotions is no longer running.");
        promotion.setActive(false);
    }

    @Autowired
    private PromotionRepository promotionRepository;

    @Override
    public List<Promotion> getAllPromotions(){

        return promotionRepository.findAll();
    }

    @Override
    public void savePromotion(PromotionDto promotiondto){
        Promotion promotion = new Promotion(promotiondto.getDescription(),promotiondto.getDiscountAmount(),
                promotiondto.getImageUrl(),promotiondto.getPromoCode(),promotiondto.getTitle());
        promotionRepository.save(promotion);
    }

}
