package com.example.CinemaEbookingSystem.model;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer extends User {

    String user_status;

    public void editProfile() {}

    public void searchMovies() {}

    public void bookTickets() {}

    public void checkout() {}

    public void viewOrderHistory() {}

    public void applyPromotion() {}
}
