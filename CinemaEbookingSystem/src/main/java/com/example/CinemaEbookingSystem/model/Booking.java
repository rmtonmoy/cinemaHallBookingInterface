package com.example.CinemaEbookingSystem.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
public class Booking {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "booking")
    private List<Ticket> ticketList = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
