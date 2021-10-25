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
    @JoinColumn(name= "booking_id")
    private Booking booking;

    @Enumerated(EnumType.STRING)
    private TicketType ticketType; 

    @OneToOne
    @JoinColumn(name = "theater_id")
    Theater theater;

    @OneToOne
    @JoinColumn(name = "show_time_id")
    ShowTime showTime;

    @OneToOne
    @JoinColumn(name = "movie_info_id")
    MovieInfo movieInfo;

    @OneToOne
    @JoinColumn(name = "seat_id")
    Seat seat;

    @OneToOne
    @JoinColumn(name = "promotion_id")
    Promotion promotion;

    public Seat getSeat() {
        return seat;
    }

    public MovieInfo getMovieInfo() {
        return movieInfo;
    }


    public ShowTime getShowTime() {
        return showTime;
    }

    public Theater getTheater() {
        return theater;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }

    public void setMovieInfo(MovieInfo movieInfo) {
        this.movieInfo = movieInfo;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
}
