package com.example.CinemaEbookingSystem.dto;

public class CheckoutDto {
    String paymentCardId;
    String promoCode;

    public CheckoutDto(){

    }


    public CheckoutDto(String paymentCardId, String promoCode) {
        this.paymentCardId = paymentCardId;
        this.promoCode = promoCode;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getPaymentCardId() {
        return paymentCardId;
    }

    public void setPaymentCardId(String paymentCardId) {
        this.paymentCardId = paymentCardId;
    }
}
