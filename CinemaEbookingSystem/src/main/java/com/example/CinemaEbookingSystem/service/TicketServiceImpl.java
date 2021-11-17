package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.model.OneShow;
import com.example.CinemaEbookingSystem.model.Theater;
import com.example.CinemaEbookingSystem.model.Ticket;
import com.example.CinemaEbookingSystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public void addTickets(OneShow oneShow) {
        Theater theater = oneShow.getTheater();

        for(int row = 1; row <= theater.getMaxR(); row++){
            for(int column = 1; column <= theater.getMaxC(); column++){
                Ticket ticket = new Ticket();
                ticket.setPromotion(null);
                ticket.setBooked(false);
                ticket.setTicketType(null);
                ticket.setTicketRn(row);
                ticket.setTicketCn(column);
                ticketRepository.save(ticket);
            }
        }
    }
}
