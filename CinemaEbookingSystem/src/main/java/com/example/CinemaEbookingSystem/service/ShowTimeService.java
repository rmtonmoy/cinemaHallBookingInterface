package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.model.ShowTime;
import org.springframework.stereotype.Component;

@Component
public interface ShowTimeService {
    ShowTime saveIfNotFound(String date, int startingAt);
}
