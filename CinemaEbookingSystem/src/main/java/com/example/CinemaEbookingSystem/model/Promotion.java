package com.example.CinemaEbookingSystem.model;

import javax.persistence.*;

@Entity
@Table(name = "promotions")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int promotion_id;
    private String promoTitle;
    private String promoDescription;
    private String promoCode;
    private int discountAmount;

    public String getPromoTitle() {
        return promoTitle;
    }

    public void setPromoTitle(String promoTitle) {
        this.promoTitle = promoTitle;
    }

    public String getPromoDescription() {
        return promoDescription;
    }

    public void setPromoDescription(String promoDescription) {
        this.promoDescription = promoDescription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
