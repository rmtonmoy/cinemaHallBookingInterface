package com.example.CinemaEbookingSystem.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class MovieSearchDto {
    
    private DateFormat dateFormat = DateFormat.getDateInstance();
    
    // If you make any of these private, I will be very angry
    // -James
    public String title;
    public String cat; // category
    public String status;
    public String date; // has to be string since it's delivered from the frontend as a string
    
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
    
    public Date getDate() {
        if (date != null) {
            try {
                Date d = dateFormat.parse(date);
                return d;
            } catch(ParseException e) {
                return null;
            }
        }
        return null;
    }
    public void setDate(String d) {
        date = d;
    }
    
}
