package com.example.CinemaEbookingSystem.model;

import javax.persistence.*;

@Entity
@Table
public class Ticket {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "show_id")
    OneShow oneShow;

    private int ticketRn;
    private int ticketCn;

    @Enumerated(EnumType.STRING)
    private TypeOfTicket typeOfTicket;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    Promotion promotion;

    boolean isBooked;

    public Ticket(){

    }

    public Ticket(OneShow oneShow, int ticketRn, int ticketCn, TypeOfTicket typeOfTicket, Promotion promotion, boolean isBooked) {
        this.oneShow = oneShow;
        this.ticketRn = ticketRn;
        this.ticketCn = ticketCn;
        this.typeOfTicket = typeOfTicket;
        this.promotion = promotion;
        this.isBooked = isBooked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OneShow getShow() {
        return oneShow;
    }

    public void setShow(OneShow oneShow) {
        this.oneShow = oneShow;
    }

    public int getTicketRn() {
        return ticketRn;
    }

    public void setTicketRn(int ticketRn) {
        this.ticketRn = ticketRn;
    }

    public int getTicketCn() {
        return ticketCn;
    }

    public void setTicketCn(int ticketCn) {
        this.ticketCn = ticketCn;
    }

    public TypeOfTicket getTicketType() {
        return typeOfTicket;
    }

    public void setTicketType(TypeOfTicket typeOfTicket) {
        this.typeOfTicket = typeOfTicket;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
