package com.example.CinemaEbookingSystem.repository;

import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.model.Ticket;
import com.example.CinemaEbookingSystem.model.TicketPrice;
import com.example.CinemaEbookingSystem.model.TypeOfTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TicketPriceRepository extends JpaRepository<TicketPrice, Long> {
    @Transactional
    @Modifying
    @Query(
            value = "UPDATE ticket_price set price = ?2 WHERE type_of_ticket = ?1", nativeQuery = true)
    void updatePrice(String Type,String price);

    @Query(
            value = "SELECT price FROM ticket_price WHERE type_of_ticket = ?1",
            nativeQuery = true)
    String getPriceByType(String typeOfTicket);
}
