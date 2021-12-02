package com.example.CinemaEbookingSystem.model;

public class TheaterStats {
    
    public long        id;
    public int         rows;
    public int         cols;
    public boolean[][] availability;
    
    public TheaterStats(long id, int rows, int cols, boolean[][] avail) {
        this.id      = id;
        this.rows    = rows;
        this.cols    = cols;
        availability = avail;
    }
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getCols() {
        return cols;
    }
    
    public boolean[][] getAvailability() {
        return availability;
    }
    public void setAvailability(boolean[][] avail) {
        availability = avail;
    }
    
}
