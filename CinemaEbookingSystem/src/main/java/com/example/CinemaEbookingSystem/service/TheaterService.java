package com.example.CinemaEbookingSystem.service;
import com.example.CinemaEbookingSystem.model.Theater;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TheaterService {
    Theater findById(Long Id);
    void addTheater(int maxR, int maxC);
    List<Theater> getAllTheaters();
}
