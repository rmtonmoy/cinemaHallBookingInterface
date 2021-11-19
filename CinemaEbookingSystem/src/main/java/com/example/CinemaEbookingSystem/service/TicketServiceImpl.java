package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.model.MovieInfo;
import com.example.CinemaEbookingSystem.model.OneShow;
import com.example.CinemaEbookingSystem.model.Theater;
import com.example.CinemaEbookingSystem.model.Ticket;
import com.example.CinemaEbookingSystem.repository.TicketRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    public boolean hasTimeConflict(OneShow oneShow){
        MovieInfo movieInfo = oneShow.getMovieInfo();
        List<Ticket> ticketList = ticketRepository.findAll();

        int startB = oneShow.getShowTime().getStartingTime();
        int endB = startB + oneShow.getMovieInfo().getDuration();

        for(Ticket ticket: ticketList){
            if(ticket.getShow().getShowTime().getDate().equals(oneShow.getShowTime().getDate()) == false){
                continue;
            }

            if(ticket.getShow().getTheater().getId() != oneShow.getTheater().getId()){
                continue;
            }

            int startA = (ticket.getShow()).getShowTime().getStartingTime();
            int endA = startA + ticket.getShow().getMovieInfo().getDuration();

            int maxStart = Math.max(startA, startB);
            int minEnd = Math.min(endA, endB);

            if(maxStart <= minEnd){
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean addTickets(OneShow oneShow) {
        if(hasTimeConflict(oneShow)){
            return false;
        }
        Theater theater = oneShow.getTheater();
        List<Ticket> ticketList = ticketRepository.findAll();

        for(int row = 1; row <= theater.getMaxR(); row++){
            for(int column = 1; column <= theater.getMaxC(); column++){
                Ticket ticket = new Ticket();
                ticket.setPromotion(null);
                ticket.setBooked(false);
                ticket.setTicketType(null);
                ticket.setTicketRn(row);
                ticket.setTicketCn(column);
                ticket.setShow(oneShow);

                /*boolean alreadyExists = false;
                for(Ticket anotherTicket : ticketList){
                    if( (anotherTicket.getShow()).getId() == oneShow.getId()
                        && anotherTicket.getTicketCn() == ticket.getTicketCn()
                        && anotherTicket.getTicketRn() == ticket.getTicketRn()){
                        alreadyExists = true;
                        break;
                    }
                }

                if(alreadyExists == false){
                    ticketRepository.save(ticket);
                }*/

                ticketRepository.save(ticket);
            }
        }

        return true;
    }
}
