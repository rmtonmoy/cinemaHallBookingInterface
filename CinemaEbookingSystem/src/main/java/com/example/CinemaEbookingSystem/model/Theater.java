package com.example.CinemaEbookingSystem.model;

import javax.persistence.*;

@Entity
@Table
public class Theater {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int maxR;
    private int maxC;

    public Theater(){

    }

    public Theater(int maxR, int maxC) {
        this.maxR = maxR;
        this.maxC = maxC;
    }

    public int getMaxR() {
        return maxR;
    }

    public void setMaxR(int maxR) {
        this.maxR = maxR;
    }

    public int getMaxC() {
        return maxC;
    }

    public void setMaxC(int maxC) {
        this.maxC = maxC;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
