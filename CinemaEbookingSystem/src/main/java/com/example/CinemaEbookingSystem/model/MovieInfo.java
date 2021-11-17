package com.example.CinemaEbookingSystem.model;

import com.example.CinemaEbookingSystem.dto.MovieInfoDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MovieInfo {

    @Id
    @GeneratedValue( strategy=GenerationType.AUTO )
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
}
