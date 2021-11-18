package com.example.CinemaEbookingSystem.repository;

import com.example.CinemaEbookingSystem.model.Ticket;
import com.example.CinemaEbookingSystem.service.TicketService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
