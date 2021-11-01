package com.example.CinemaEbookingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.CinemaEbookingSystem.model.PaymentCard;

import java.util.List;


@Repository
public interface PaymentCardRepository extends JpaRepository<PaymentCard, Long> {

    @Query(
        value = "SELECT * FROM paymentcards WHERE fk_cid = :customerID",
        nativeQuery = true)
    List<PaymentCard> findPaymentCardsById(@Param("customerID") long customerID);


}
