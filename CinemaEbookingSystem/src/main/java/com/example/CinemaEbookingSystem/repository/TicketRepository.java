package com.example.CinemaEbookingSystem.repository;

import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.model.Ticket;
import com.example.CinemaEbookingSystem.service.TicketService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query(
            value = "SELECT COUNT (id) FROM ticket WHERE customer_id = ?1",
            nativeQuery = true)
    int getQTYByCID(long CID);

    @Query(
            value = "SELECT * FROM ticket WHERE customer_id = ?1",
            nativeQuery = true)
    List<Ticket> findByCID(long CID);

    @Query(
            value = "SELECT * FROM ticket WHERE id = ?1",
            nativeQuery = true)
    Ticket findByID(long ID);

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE ticket set customer_id = 0 WHERE id = ?1", nativeQuery = true)
    void deleteFromCart(long id);
}
