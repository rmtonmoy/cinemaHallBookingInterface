package com.example.CinemaEbookingSystem.dto;

import com.example.CinemaEbookingSystem.model.Customer;

public class PaymentCardDto {
    private long cardNumber;
    private int securityCode;
    private String cardHolder;
    private String cardType;
    private String expirationDate;
    private String billingAddress;
    private Customer customer;

    public PaymentCardDto(){

    }
    public PaymentCardDto(long cardNumber, int securityCode, String cardHolder, String cardType, String expirationDate, String billingAddress) {
        super();
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
        this.cardHolder = cardHolder;
        this.cardType = cardType;
        this.expirationDate = expirationDate;
        this.billingAddress = billingAddress;
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
}
