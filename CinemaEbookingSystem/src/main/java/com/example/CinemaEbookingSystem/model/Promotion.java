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
    private boolean active;

    public Promotion(int promotion_id, String promoTitle, String promoDescription, String promoCode, int discountAmount, boolean active) {
        this.promotion_id = promotion_id;
        this.promoTitle = promoTitle;
        this.promoDescription = promoDescription;
        this.promoCode = promoCode;
        this.discountAmount = discountAmount;
        this.active = true;
    }


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

    public boolean isActive() { return active; }

    public void setActive(boolean active) { this.active = active; }
}
