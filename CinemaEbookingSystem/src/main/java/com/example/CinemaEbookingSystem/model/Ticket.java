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
}
