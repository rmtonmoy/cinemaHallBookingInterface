package com.example.CinemaEbookingSystem.dto;

public class MovieSearchDto {
    
    // If you make any of these private, I will be very angry
    // -James
    public String title;
    public String cat; // category
    public String status;
    
    // I cannot believe thymeleaf is forcing me to make some blasted one-line getter/setter pairs, jfc
    // I'm not coming within 500 trillion light years of this """""""""""software""""""""""" after this class
    public String getTitle() {
        return title;
    }
    public void setTitle(String s) {
        title = s;
    }
    
    public String getCat() {
        return cat;
    }
    public void setCat(String s) {
        cat = s;
    }
    
    public String getStatus() {
        return status;
    }
    public void setStatus(String s) {
        status = s;
    }
    
}
