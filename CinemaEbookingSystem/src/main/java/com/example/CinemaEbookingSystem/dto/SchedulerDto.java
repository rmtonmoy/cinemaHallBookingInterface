package com.example.CinemaEbookingSystem.dto;

import java.util.Date;

public class SchedulerDto {
    String movieTitle;
    Date date;
    int theaterId;
    int StartTimeHr;
    int StartTimeMin;
    String AmPm;

    public SchedulerDto() {
    }

    public int getStartingTimeInMinutes(){
        int startingTime = 0;
        if(AmPm.equals("PM")){
            startingTime += 12 * 60;
        }

        if(StartTimeHr == 12){
            return startingTime + StartTimeMin;
        }

        startingTime += StartTimeHr * 60 + StartTimeMin;
        return startingTime;
    }

    public SchedulerDto(String movieTitle, Date date, int theaterId, int startTimeHr, int startTimeMin, String amPm) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
