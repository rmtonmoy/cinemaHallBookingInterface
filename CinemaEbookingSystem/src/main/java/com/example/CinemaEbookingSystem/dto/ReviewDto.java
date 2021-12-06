package com.example.CinemaEbookingSystem.dto;

import com.example.CinemaEbookingSystem.repository.ReviewRepository;

public class ReviewDto {
    int movieId;
    String review;

    public ReviewDto(){

    }
    public ReviewDto(int movieId, String review) {
        this.movieId = movieId;
        this.review = review;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
