package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.model.OneShow;
import com.example.CinemaEbookingSystem.model.Ticket;
import com.example.CinemaEbookingSystem.model.TicketPrice;
import com.example.CinemaEbookingSystem.model.TypeOfTicket;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TicketService {
    boolean addTickets(OneShow oneShow);
    List<List<String>> generateSchedule();
    List<String> generateSchedule(int id);
    List<TicketPrice> allTicketPrice();
    void savePrice(TypeOfTicket typeOfTicket,String price);
    List<Ticket> getTicketsForShowId(long id);
}
