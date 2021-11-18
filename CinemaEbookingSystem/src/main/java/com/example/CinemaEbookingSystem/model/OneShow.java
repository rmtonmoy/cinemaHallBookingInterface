package com.example.CinemaEbookingSystem.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class OneShow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name= "showTime_id")
    ShowTime showTime;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    Theater theater;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    MovieInfo movieInfo;

    public OneShow(){

    }

    public OneShow(ShowTime showTime, Theater theater, MovieInfo movieInfo) {
        this.showTime = showTime;
        this.theater = theater;
        this.movieInfo = movieInfo;
    }

    public OneShow(Theater theater, MovieInfo movieInfo) {
        this.theater = theater;
        this.movieInfo = movieInfo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public MovieInfo getMovieInfo() {
        return movieInfo;
    }

    public void setMovieInfo(MovieInfo movieInfo) {
        this.movieInfo = movieInfo;
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }
}
