package com.example.CinemaEbookingSystem.model;

import com.example.CinemaEbookingSystem.repository.TicketPriceRepository;
import com.example.CinemaEbookingSystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CartItem {
    private long id;
    private long customerId;
    private String movieName;
    private String showTime;
    private String ticketType;
    private String price;

    public CartItem() {
    }

    public CartItem(long ticketid, long customerId, OneShow show, TypeOfTicket typeOfTicket, String price) {
        this.id = ticketid;
        this.customerId = customerId;
        this.movieName = show.movieInfo.getTitle();
        this.showTime =  "Theater "+show.getTheater().getId().toString()+ " "+show.getShowTime().getDateString()+" "
                        + show.getShowTime().getStartingTimeString();
        this.ticketType = typeOfTicket.toString();
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getShowTime() {
        return showTime;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
