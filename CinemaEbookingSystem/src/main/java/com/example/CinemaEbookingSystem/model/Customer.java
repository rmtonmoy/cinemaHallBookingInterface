package com.example.CinemaEbookingSystem.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Customer extends User {

    //String user_status;
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<PaymentCard> cardlist = new ArrayList<>();

    private boolean isRegistered;

    //@ManyToMany
    //@JoinTable(name="OrderHistory",
    //        joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
    //inverseJoinColumns = @JoinColumn(name = "booking_id", referencedColumnName = "id"))

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Booking> booking = new ArrayList<>();

    public Customer(){

    }

    public Customer(UserStatus status, List<PaymentCard> cardlist, boolean isRegistered) {
        super();
        this.status = status;
        this.cardlist = cardlist;
        this.isRegistered = isRegistered;
    }
    public Customer(String firstName, String lastName, String password, String email, String dob) {
        super(firstName, lastName, password, email, dob);
    }


    public Customer(String firstName, String lastName, String password, String email, String dob, UserStatus userStatus, boolean isRegistered) {
        super(firstName, lastName, password, email, dob);
        this.status = userStatus;
        this.isRegistered = isRegistered;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public List<PaymentCard> getCardlist() {
        return cardlist;
    }

    public void setCardlist(List<PaymentCard> cardlist) {
        this.cardlist = cardlist;
    }

    public void addPaymentCard(PaymentCard paymentCard) {
        this.cardlist.add(paymentCard);
    }

    public List<Booking> getBooking() {
        return booking;
    }

    public void setBooking(List<Booking> booking) {
        this.booking = booking;
    }

    public void editProfile() {}

    public void searchMovies() {}

    public void bookTickets() {}

    public void checkout() {}

    public void viewOrderHistory() {}

    public void applyPromotion() {}
}
