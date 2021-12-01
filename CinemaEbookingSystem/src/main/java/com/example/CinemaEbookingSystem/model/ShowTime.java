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
    Date date;
    int startingTime;

    public ShowTime(){

    }

    public ShowTime(Date date, int startingTime) {
        this.date = date;
        this.startingTime = startingTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
    
    public String getDateString() {
        return "" + (date.getMonth() + 1) + "/" + date.getDate();
    }

    public String getDateStringWithYear(){
        return (date.getMonth()+1) + "/" + date.getDate() + "/" + (date.getYear()+1900);
    }
}
