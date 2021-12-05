package com.example.CinemaEbookingSystem.dto;

import com.example.CinemaEbookingSystem.model.Ticket;
import com.example.CinemaEbookingSystem.model.TypeOfTicket;
import com.example.CinemaEbookingSystem.service.TicketService;
import java.util.List;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import org.springframework.beans.factory.annotation.Autowired;

public class BookingDto {
    public long   showingId;
    public String ticketsParam; // index:type:row:col;
    
    @Autowired
    private TicketService ticketService;
    
    public BookingDto(long showing, String tickets) {
        showingId    = showing;
        ticketsParam = tickets;
    }
    
    public long getShowingId() {
        return showingId;
    }
    public void setShowingId(long s) {
        showingId = s;
    }
    
    public String getTicketsParam() {
        return ticketsParam;
    }
    public void setTicketsParam(String s) {
        ticketsParam = s;
    }
    
    /** Parses `ticketsParam` and returns a `List` of `Ticket`s with matching rows and columns for the correct showing.
      * The ticket type will be updated (but only in memory, i.e. <b>NOT</b> in the database!!!) to the proper type
      * based on the user's input.
      * @return A `List` of `Ticket`s that the customer added to their cart.
      * @throws RuntimeException If `ticketsParam` is malformed. Format is `index:type:row:col;`.
      */
    public List<Ticket> getTickets() throws RuntimeException {
        List<Ticket>      tickets = ticketService.getTicketsForShowId(showingId);
        ArrayList<Ticket> temp    = new ArrayList<>();
        
        int startingIndex, workingIndex, endIndex;
        startingIndex = 0;
        endIndex      = ticketsParam.indexOf(";");
        
        // I HATE using exceptions like this but it's worth saving potential headaches right now I think -james
        if (endIndex < 0) {
            throw new RuntimeException("BookingDto.ticketsParam is malformed (no semicolons)");
        }
        
        while (endIndex > -1) {
            Ticket ticket = new Ticket(); // index:type:row:col;
            
            startingIndex = ticketsParam.indexOf(":", startingIndex);
            if (startingIndex < 0) {
                throw new RuntimeException("BookingDto.ticketsParam is malformed (no colons)");
            }
            workingIndex = ticketsParam.indexOf(":", startingIndex + 1);
            if (workingIndex < 0) {
                throw new RuntimeException("BookingDto.ticketsParam is malformed (missing colon separating type and row)");
            }
            String ticketType = ticketsParam.substring(startingIndex + 1, workingIndex);
            
            startingIndex = ticketsParam.indexOf(":", workingIndex + 1);
            if (startingIndex < 0) {
                throw new RuntimeException("BookingDto.ticketsParam is malformed (missing colon separating row and col)");
            }
            int row;
            try {
                row = Integer.parseInt(ticketsParam.substring(workingIndex + 1, startingIndex));
            } catch(NumberFormatException e) {
                throw new RuntimeException("BookingDto.ticketsParam is malformed (row value is not a parseable integer)");
            }
            
            int col;
            try {
                col = Integer.parseInt(ticketsParam.substring(startingIndex + 1, endIndex));
            } catch(NumberFormatException e) {
                throw new RuntimeException("BookingDto.ticketsParam is malformed (col value is not a parseable integer)");
            }
            
            ticket.setTicketRn(row);
            ticket.setTicketCn(col);
            if (ticketType.equals("adult")) { // wow this is disgusting
                ticket.setTypeOfTicket(TypeOfTicket.Adult);
            } else if (ticketType.equals("child")) {
                ticket.setTypeOfTicket(TypeOfTicket.Child);
            } else if (ticketType.equals("student")) {
                ticket.setTypeOfTicket(TypeOfTicket.Student);
            } else if (ticketType.equals("senior")) {
                ticket.setTypeOfTicket(TypeOfTicket.Senior);
            }
            
            temp.add(ticket);
            startingIndex = endIndex + 1;
            endIndex      = ticketsParam.indexOf(";", startingIndex);
        }
        
        tickets.removeIf((Ticket t) -> {
            if (temp.isEmpty()) return true;
            for (Ticket t2 : temp) {
                if (t.getTicketRn() == t2.getTicketRn() && t.getTicketCn() == t2.getTicketCn()) {
                    t.setTicketType(t2.getTicketType());
                    temp.remove(t2);
                    return false;
                }
            }
            return true;
        });
        return tickets;
    }
}
