package com.example.CinemaEbookingSystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GeneratedType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "promotions")
public class promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int promotion_id;
    private String promoCode;
    private int discountAmount;

    public int getPromotion_id() {
        return promotion_id;
    }

    public void setPromotion_id(int promotion_id) {
        this.promotion_id = promotion_id;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }
}
