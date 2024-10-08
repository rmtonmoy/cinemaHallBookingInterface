package com.example.CinemaEbookingSystem.model;

import com.example.CinemaEbookingSystem.dto.MovieInfoDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MovieInfo {

    @Id
    @GeneratedValue( strategy=GenerationType.IDENTITY )
    private int id;
    
    private String title;
    private String category;

    @OneToMany( mappedBy = "movie")
    private List<Review> reviewlist = new ArrayList<>();

    private String cast;
    private String director;
    private String producer;
    private String synopsis;
    private String MPAA_rating;
    private String linkToTrailer;
    private String linkToPoster;
    private int duration;

    public MovieInfo(MovieInfoDto movieInfoDto) {
        title = movieInfoDto.getTitle();
        category = movieInfoDto.getCategory();
        cast = movieInfoDto.getCast();
        director = movieInfoDto.getDirector();
        producer = movieInfoDto.getProducer();
        synopsis = movieInfoDto.getSynopsis();
        MPAA_rating = movieInfoDto.getMPAA_rating();
        linkToTrailer = movieInfoDto.getLinkToTrailer();
        linkToPoster = movieInfoDto.getLinkToPoster();
    }
    
    // TODO - Constructor
    public MovieInfo() {};

    public MovieInfo(String title, String category, String cast, String director, String producer, String synopsis, String MPAA_rating, int duration, String linkToPoster, String linkToTrailer) {
        this.title = title;
        this.category = category;
        this.cast = cast;
        this.director = director;
        this.producer = producer;
        this.synopsis = synopsis;
        this.MPAA_rating = MPAA_rating;
        this.duration = duration;
        this.linkToPoster = linkToPoster;
        this.linkToTrailer = linkToTrailer;
    }

    // temp constructor for demo use in CheckoutController
    public MovieInfo(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Review> getReviewlist() {
        return reviewlist;
    }

    public void setReviewlist(List<Review> reviewlist) {
        this.reviewlist = reviewlist;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getMPAA_rating() {
        return MPAA_rating;
    }

    public void setMPAA_rating(String MPAA_rating) {
        this.MPAA_rating = MPAA_rating;
    }
    
    public String getLinkToTrailer() {
        return linkToTrailer;
    }
    
    public String getLinkToPoster() {
        return linkToPoster;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
