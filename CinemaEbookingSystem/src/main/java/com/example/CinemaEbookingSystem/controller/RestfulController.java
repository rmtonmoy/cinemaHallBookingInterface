package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.model.Theater;
import com.example.CinemaEbookingSystem.model.TheaterStats;
import com.example.CinemaEbookingSystem.model.Ticket;
import com.example.CinemaEbookingSystem.service.TicketService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestfulController {
    @Autowired
    TicketService ticketService;
    
    @GetMapping(path = "/book/gettheaterstats")
    public TheaterStats getTheaterStats(@RequestParam(value = "id") long id) {
        List<Ticket> tickets = ticketService.getTicketsForShowId(id);
        Theater theater      = tickets.get(0).getOneShow().getTheater();
        boolean[][] avail    = new boolean[theater.getMaxR()][theater.getMaxC()];
        for (Ticket ticket : tickets) {
            avail[ticket.getTicketRn() - 1][ticket.getTicketCn() - 1] = ticket.isInCart() || ticket.isPurchased();
        }
        return new TheaterStats(id, theater.getMaxR(), theater.getMaxC(), avail);
    }
}
