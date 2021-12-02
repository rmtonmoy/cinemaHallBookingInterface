package com.example.CinemaEbookingSystem.model;

public class TheaterStats {
    
    public long        id;
    public boolean[][] availability;
    
    public TheaterStats(long id, boolean[][] avail) {
        this.id      = id;
        availability = avail;
    }
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
    public boolean[][] getAvailability() {
        return availability;
    }
    public void setAvailability(boolean[][] avail) {
        availability = avail;
    }
    
}
