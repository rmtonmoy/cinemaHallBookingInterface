package com.example.CinemaEbookingSystem.model;

import javax.persistence.*;

@Entity
@Table(name = "paymentcards")
public class PaymentCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int cardNumber;

    private String expirationDate;

    private String billingAddress;

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    @ManyToOne
    @JoinColumn(name= "fk_CID")
    private Customer customer;
}
