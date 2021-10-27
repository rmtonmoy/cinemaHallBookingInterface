package com.example.CinemaEbookingSystem.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer extends User {

    //String user_status;
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToMany( mappedBy = "customer")
    private List<PaymentCard> cardlist = new ArrayList<>();

    public void editProfile() {}

    public void searchMovies() {}

    public void bookTickets() {}

    public void checkout() {}

    public void viewOrderHistory() {}

    public void applyPromotion() {}
}
