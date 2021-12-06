package com.example.CinemaEbookingSystem.model;

import javax.persistence.*;

@Entity
public class TicketPrice {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeOfTicket typeOfTicket;

    private String price;

    private String bookingFee;

    public TicketPrice(TypeOfTicket typeOfTicket, String price) {
        this.typeOfTicket= typeOfTicket;
        this.price= price;
        this.bookingFee = "2";
    }

    public String getBookingFee() {
        return bookingFee;
    }

    public void setBookingFee(String bookingFee) {
        this.bookingFee = bookingFee;
    }

    public TicketPrice(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeOfTicket getTypeOfTicket() {
        return typeOfTicket;
    }

    public void setTypeOfTicket(TypeOfTicket typeOfTicket) {
        this.typeOfTicket = typeOfTicket;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
