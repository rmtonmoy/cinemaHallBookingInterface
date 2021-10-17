package com.example.CinemaEbookingSystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GeneratedType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void editProfile() {}

    public void searchMovies() {}

    public void bookTickets() {}

    public void checkout() {}

    public void viewOrderHistory() {}

    public void applyPromotion() {}
}
