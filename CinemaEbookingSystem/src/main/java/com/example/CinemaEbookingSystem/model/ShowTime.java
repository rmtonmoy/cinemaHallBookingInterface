package com.example.CinemaEbookingSystem.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table
@Entity
public class ShowTime {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Denotes a particular minute of the day
    // Example:
    // 12am     -> 0
    // 1 am     -> 60
    // 1:03 am  -> 63
    // 11:59 pm -> 1439 [ 24 * 60 - 1]
    String Date;
    int startingTime;

    public ShowTime(){

    }

    public ShowTime(String date, int startingTime) {
        Date = date;
        this.startingTime = startingTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(int startingTime) {
        this.startingTime = startingTime;
    }
    
    public String getStartingTimeString() {
        int     hour = startingTime / 60;
        int     min  = startingTime % 60;
        boolean pm   = false;
        if (hour >= 12) {
            hour -= 12;
            pm    = true;
        }
        return "" + hour + ":" + (min < 10 ? "0" : "") + min + " " + (pm ? "PM" : "AM");
    }
}
