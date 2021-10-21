package com.example.CinemaEbookingSystem.repository;

import com.example.CinemaEbookingSystem.model.PaymentCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.CinemaEbookingSystem.model.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long>{

}
