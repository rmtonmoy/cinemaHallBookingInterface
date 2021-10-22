package com.example.CinemaEbookingSystem.model;

import javax.persistence.*;

@Entity
@Table(name = "Reviews")
public class Reviews {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int rid;
    private String review;

    @ManyToOne
    @JoinColumn(name= "fk_MID")
    private MovieInfo movie;

    public Reviews(int rid, String review) {
        super();
        this.rid = rid;
        this.review = review;
    }

    public Reviews(){
        super();
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public MovieInfo getMovie() {
        return movie;
    }

    public void setMovie(MovieInfo movie) {
        this.movie = movie;
    }
}

