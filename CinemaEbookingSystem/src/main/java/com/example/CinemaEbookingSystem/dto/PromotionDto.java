package com.example.CinemaEbookingSystem.dto;

public class PromotionDto {
    private String description;
    private int discountAmount;
    private String imageUrl;
    private String promoCode;
    private String title;

    public PromotionDto() {
    }

    public PromotionDto(String description, int discountAmount, String imageUrl, String promoCode, String title) {
        this.description = description;
        this.discountAmount = discountAmount;
        this.imageUrl = imageUrl;
        this.promoCode = promoCode;
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
