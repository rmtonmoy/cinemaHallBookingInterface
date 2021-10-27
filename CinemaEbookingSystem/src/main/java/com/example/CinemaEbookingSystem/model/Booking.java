package com.example.CinemaEbookingSystem.model;

import javax.persistence.*;
import java.nio.MappedByteBuffer;
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

    @OneToOne
    @JoinColumn(name = "card_info")
    private PaymentCard paymentCard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
