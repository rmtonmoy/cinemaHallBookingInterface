package com.example.CinemaEbookingSystem.model;

import java.util.Base64;

import javax.persistence.*;

@Entity
@Table(name = "paymentcards")
public class PaymentCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String cardNumber;

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
    
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String decodeCardNumber(String cardNumber) {
        String decodedCardNumber = new String(Base64.getDecoder().decode(cardNumber));
        return decodedCardNumber;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_CID", nullable = false)
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void assignToCustomer(Customer customer) {
        customer.addPaymentCard(this);
        this.customer = customer;
    }
}
