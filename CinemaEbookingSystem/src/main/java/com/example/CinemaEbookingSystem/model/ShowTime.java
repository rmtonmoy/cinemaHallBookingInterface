package com.example.CinemaEbookingSystem.model;

import javax.persistence.*;
import java.util.Date;

@Table
@Entity
public class ShowTime {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public int getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(int startingTime) {
        this.startingTime = startingTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Denotes a particular minute of the day
    // Example:
    // 12am     -> 0
    // 1 am     -> 60
    // 1:03 am  -> 63
    // 11:59 pm -> 1439 [ 24 * 60 - 1]
    int startingTime;
    Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
