package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.dto.PromotionDto;
import com.example.CinemaEbookingSystem.model.Promotion;
import com.example.CinemaEbookingSystem.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PromotionController {
    @Autowired
    private PromotionService promotionService;

    @ModelAttribute("promotion")
    public PromotionDto promotiondto(){ return new PromotionDto();}

    @GetMapping(path = "/managePromo")
    public String showAllPromo(Model model){

        model.addAttribute("listPromo", promotionService.getAllPromotions());

        List<Promotion> promotionList = promotionService.getAllPromotions();

        return "managePromo";
    }

    @PostMapping(path = "/managePromo")
    public String savePromo(@ModelAttribute("promotion") PromotionDto promotiondto){
        promotionService.savePromotion(promotiondto);
        return "redirect:/managePromo";
    }

}
