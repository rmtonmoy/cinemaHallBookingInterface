package com.example.CinemaEbookingSystem.dto;

import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.model.PaymentCard;

public class PaymentCardDto {
    private String cardNumber;
    private String securityCode;
    private String cardHolder;
    private String cardType;
    private String expirationDate;
    private String billingAddress;
    private Customer customer;

    public PaymentCardDto(){

    }
    public PaymentCardDto(String cardNumber, String securityCode, String cardHolder, String cardType, String expirationDate, String billingAddress) {
        super();
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
        this.cardHolder = cardHolder;
        this.cardType = cardType;
        this.expirationDate = expirationDate;
        this.billingAddress = billingAddress;
    }
    public PaymentCardDto(PaymentCard card) {
        cardNumber     = card.getCardNumber();
        securityCode   = card.getSecurityCode();
        cardHolder     = card.getCardHolder();
        cardType       = card.getCardType();
        expirationDate = card.getExpirationDate();
        billingAddress = card.getBillingAddress();
        customer       = card.getCustomer();
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
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
