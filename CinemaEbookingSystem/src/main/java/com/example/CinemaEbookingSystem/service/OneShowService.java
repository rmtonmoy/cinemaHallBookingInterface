package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.model.OneShow;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface OneShowService {
    OneShow save(Date date, Long theaterId, int startingAt, String movieName);
    List<OneShow> getAllShowsForMovie(int movieId);
}
