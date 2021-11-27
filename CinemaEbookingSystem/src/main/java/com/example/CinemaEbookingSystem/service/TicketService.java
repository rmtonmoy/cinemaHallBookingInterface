package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.model.OneShow;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TicketService {
    boolean addTickets(OneShow oneShow);
    List<List<String>> generateSchedule();
}
