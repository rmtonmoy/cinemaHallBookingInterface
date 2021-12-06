package com.example.CinemaEbookingSystem.service;

import org.springframework.stereotype.Component;

@Component
public interface PopulateDBService {
    void addTheater(int maxR, int maxC);
}
