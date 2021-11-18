package com.example.CinemaEbookingSystem.service;
import com.example.CinemaEbookingSystem.model.Theater;
import org.springframework.stereotype.Component;

@Component
public interface TheaterService {
    Theater findById(Long Id);
}
