package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.model.OneShow;
import org.springframework.stereotype.Component;

@Component
public interface TicketService {
    boolean addTickets(OneShow oneShow);
}
