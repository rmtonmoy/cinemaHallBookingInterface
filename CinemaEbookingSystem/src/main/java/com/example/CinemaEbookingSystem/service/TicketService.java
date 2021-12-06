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
    String getTicketPriceByType(String type);
    void savePrice(TypeOfTicket typeOfTicket,String price);
    List<Ticket> getTicketsForShowId(long id);
    Ticket getById(long id);
    String getBookingFee();
    void deleteFromCart(long id);
    void updateBookingFeeForAll(String bookingFee);
    boolean hasAtLeastOneTicket(OneShow oneShow);
    boolean canPurchaseTicket(long id);
    boolean bookTicket(long id, long customerId, TypeOfTicket typeOfTicket);
    void confirmPurchase(long customerId, long cardId, float totalPrice);
}
