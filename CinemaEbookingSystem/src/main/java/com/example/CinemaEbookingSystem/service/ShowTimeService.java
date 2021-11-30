package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.model.ShowTime;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface ShowTimeService {
    ShowTime saveIfNotFound(Date date, int startingAt);
}
