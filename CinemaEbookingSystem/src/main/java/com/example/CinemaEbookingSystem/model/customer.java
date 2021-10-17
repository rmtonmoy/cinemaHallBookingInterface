package com.example.CinemaEbookingSystem.model;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class customer extends user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public void editProfile() {}

    public void searchMovies() {}

    public void bookTickets() {}

    public void checkout() {}

    public void viewOrderHistory() {}

    public void applyPromotion() {}
}
