package com.example.CinemaEbookingSystem.model;

import javax.persistence.*;

@Entity
@Table
public class Seat {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private char r;
    private int c;

    public char getR() {
        return r;
    }

    public void setR(char r) {
        this.r = r;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
