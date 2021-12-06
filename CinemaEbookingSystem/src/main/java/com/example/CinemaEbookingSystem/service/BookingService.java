package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.model.Booking;
import org.springframework.stereotype.Component;

@Component
public interface BookingService {
    void save(Booking booking);
}
