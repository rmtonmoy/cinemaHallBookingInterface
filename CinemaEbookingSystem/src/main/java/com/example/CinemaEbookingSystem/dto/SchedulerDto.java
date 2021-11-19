package com.example.CinemaEbookingSystem.dto;

public class SchedulerDto {
    String movieTitle;
    String date;
    int theaterId;
    int StartTimeHr;
    int StartTimeMin;
    String AmPm;

    public SchedulerDto() {
    }

    public SchedulerDto(String movieTitle, String date, int theaterId, int startTimeHr, int startTimeMin, String amPm) {
        this.movieTitle = movieTitle;
        this.date = date;
        this.theaterId = theaterId;
        StartTimeHr = startTimeHr;
        StartTimeMin = startTimeMin;
        AmPm = amPm;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public int getStartTimeHr() {
        return StartTimeHr;
    }

    public void setStartTimeHr(int startTimeHr) {
        StartTimeHr = startTimeHr;
    }

    public int getStartTimeMin() {
        return StartTimeMin;
    }

    public void setStartTimeMin(int startTimeMin) {
        StartTimeMin = startTimeMin;
    }

    public String getAmPm() {
        return AmPm;
    }

    public void setAmPm(String amPm) {
        AmPm = amPm;
    }
}
