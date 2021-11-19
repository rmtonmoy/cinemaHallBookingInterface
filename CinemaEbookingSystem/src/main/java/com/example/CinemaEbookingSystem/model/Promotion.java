package com.example.CinemaEbookingSystem.model;

import javax.persistence.*;

@Entity
@Table(name = "promotion")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String promoTitle;
    private String promoDescription;
    private String promoCode;
    private int discountAmount;
    private String imageUrl;
    private boolean isSent;
    private boolean active;

    public Promotion(){

    }

    public Promotion(String promoTitle, String promoDescription, String promoCode, int discountAmount, boolean active) {
        this.promoTitle = promoTitle;
        this.promoDescription = promoDescription;
        this.promoCode = promoCode;
        this.discountAmount = discountAmount;
        this.active = true;
        this.isSent = false;
    }
    public Promotion(String description, int discountAmount, String imageUrl, String promoCode, String title) {
        this.promoDescription = description;
        this.discountAmount = discountAmount;
        this.imageUrl = imageUrl;
        this.promoCode = promoCode;
        this.promoTitle = title;
        this.active = true;
        this.isSent = false;
    }

    public boolean isIsSent() {
        return isSent;
    }

    public void setIsSent(boolean sent) {
        isSent = sent;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
