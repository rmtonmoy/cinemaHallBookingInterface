package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.model.MovieInfo;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface MovieInfoService {
    List<MovieInfo> getAllMovieInfo();
    void SaveMovieInfo(MovieInfo movieInfo);
    List<String> getCategories();
    boolean hasMovie(String title);
    MovieInfo findByTitle(String title);
}
