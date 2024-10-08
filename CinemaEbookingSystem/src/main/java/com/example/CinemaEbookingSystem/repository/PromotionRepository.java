package com.example.CinemaEbookingSystem.repository;

import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.model.PaymentCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.CinemaEbookingSystem.model.Promotion;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long>{

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE promo set active = 'Active' WHERE promo = ?1", nativeQuery = true)
    void updateStatustoActive(String promo);

    @Query(
            value = "SELECT * FROM promotion WHERE is_sent = '0'",
            nativeQuery = true)
    List<Promotion> findUnsentPromotions();

    @Query(
            value = "SELECT * FROM promotion WHERE is_sent = '1'",
            nativeQuery = true)
    List<Promotion> findSentPromotions();


}
