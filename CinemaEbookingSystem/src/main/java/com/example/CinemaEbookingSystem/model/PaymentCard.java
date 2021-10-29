package com.example.CinemaEbookingSystem.model;

import javax.persistence.*;

@Entity
@Table(name = "paymentcards")
public class PaymentCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long cardNumber;

    private int securityCode;

    private String cardHolder;

    private String cardType;

    private String expirationDate;

    private String billingAddress;

    public PaymentCard() {
    }

    public PaymentCard(Customer customer) {
        this.customer = customer;
    }

    public PaymentCard(long cardNumber, int securityCode, String cardHolder, String cardType, String expirationDate, String billingAddress, Customer customer) {
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
        this.cardHolder = cardHolder;
        this.cardType = cardType;
        this.expirationDate = expirationDate;
        this.billingAddress = billingAddress;
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
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
    @JoinColumn(name = "fk_CID")
    private Customer customer;
}
