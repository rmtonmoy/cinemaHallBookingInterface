package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.model.OneShow;
import org.springframework.stereotype.Component;

@Component
public interface OneShowService {
    OneShow save(String date, Long theaterId, int startingAt, String movieName);
}
