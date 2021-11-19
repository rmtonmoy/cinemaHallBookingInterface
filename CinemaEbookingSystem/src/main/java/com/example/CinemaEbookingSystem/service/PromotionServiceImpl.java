package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.dto.PromotionDto;
import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.repository.CustomerRepository;
import com.example.CinemaEbookingSystem.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.CinemaEbookingSystem.model.Promotion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    EmailService emailService;

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public boolean sendPromoEmail(Promotion promotion) {
        List<Customer> customers= customerRepository.findCustomersByPromoRegistration();
        for (int i = 0; i < customers.size(); i++) {
            String toMail = customers.get(i).getEmail();
            emailService.sendPromotionalEmail(toMail, promotion);
        }
        promotion.setIsSent(true);
        return true;
    }

    public void discontinuePromo(Promotion promotion) {
        promotion.setPromoDescription("Sorry, this promotions is no longer running.");
        promotion.setActive(false);
    }

    @Override
    public List<Promotion> getAllPromotions(){

        return promotionRepository.findAll();
    }

    @Override
    public List<Promotion> getUnsentPromotions() {
        return promotionRepository.findUnsentPromotions();
    }

    @Override
    public List<Promotion> getSentPromotions() {
        return promotionRepository.findSentPromotions();
    }

    @Override
    public void savePromotion(PromotionDto promotiondto){
        Promotion promotion = new Promotion(promotiondto.getDescription(),promotiondto.getDiscountAmount(),
                promotiondto.getImageUrl(),promotiondto.getPromoCode(),promotiondto.getTitle());
        promotiondto.setId(promotion.getId());
        promotionRepository.save(promotion);
    }

    @Override
    public Promotion getPromoById(long id) {
        Optional<Promotion> optional= promotionRepository.findById(id);
        Promotion promotion = null;
        if(optional.isPresent()){
            promotion = optional.get();
        }
        else{
            throw new RuntimeException(" Promotion not found for id : "+id);
        }
        return promotion;
    }

    @Override
    public void updatePromotion(Promotion promotion) {
        this.promotionRepository.save(promotion);
    }

    @Override
    public void deletePromotion(Promotion promotion) {
        promotionRepository.delete(promotion);
    }

}
