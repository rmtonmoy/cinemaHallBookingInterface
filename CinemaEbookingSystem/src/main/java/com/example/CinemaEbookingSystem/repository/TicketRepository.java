package com.example.CinemaEbookingSystem.repository;

import com.example.CinemaEbookingSystem.model.Ticket;
import com.example.CinemaEbookingSystem.service.TicketService;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(
        value = "SELECT * FROM ticket WHERE show_id = :id",
        nativeQuery = true
    )
    List<Ticket> getTicketsForShowId(@Param("id") long id);
    
}
