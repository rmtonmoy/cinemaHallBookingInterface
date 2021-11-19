package com.example.CinemaEbookingSystem.dto;

public class TheaterDto {
    private int maxR;
    private int maxC;

    public TheaterDto() {

    }

    public TheaterDto(int maxR, int maxC) {
        this.maxR = maxR;
        this.maxC = maxC;
    }

    public int getMaxR() {
        return maxR;
    }

    public void setMaxR(int maxR) {
        this.maxR = maxR;
    }

    public int getMaxC() {
        return maxC;
    }

    public void setMaxC(int maxC) {
        this.maxC = maxC;
    }
}
