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

        model.addAttribute("something", "Cinema E-booking System");
        model.addAttribute("ListUnsentPromo", promotionService.getUnsentPromotions());
        model.addAttribute("ListSentPromo", promotionService.getSentPromotions());

        List<Promotion> promotionList = promotionService.getAllPromotions();

        return "managePromo";
    }

    @GetMapping(path = "/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable (value = "id") long id, Model model){
        Promotion promotion = promotionService.getPromoById(id);
        model.addAttribute("updatepromotion", promotion);
        return "updatePromotion";
    }

    @GetMapping(path = "/sendPromoToUsers/{id}")
    public String sendPromoToUsers(@PathVariable (value = "id") long id, Model model){
        Promotion promotion = promotionService.getPromoById(id);
        promotionService.sendPromoEmail(promotion);
        model.addAttribute("ListUnsentPromo", promotionService.getUnsentPromotions());
        model.addAttribute("ListSentPromo", promotionService.getSentPromotions());
        return "dummy";
    }


    @GetMapping(path = "/performDeleteOperation/{id}")
    public String performDeleteOperation(@PathVariable (value = "id") long id, Model model){
        Promotion promotion = promotionService.getPromoById(id);
        promotionService.deletePromotion(promotion);
        return "dummyPrime";
    }

    @GetMapping(path = "/dummyPrime")
    public String dummyPrime(){
        return "managePromo";
    }


    @GetMapping(path = "/dummy")
    public String dummy(){
        return "managePromo";
    }

    @PostMapping(path = "/updatePromo")
    public String savePromo(@ModelAttribute("updatepromotion") Promotion promotion){
        promotionService.updatePromotion(promotion);
        return "redirect:/managePromo";
    }

    @PostMapping(path = "/managePromo")
    public String savePromo(@ModelAttribute("promotion") PromotionDto promotiondto){
        promotionService.savePromotion(promotiondto);
        return "redirect:/managePromo";
    }

}
