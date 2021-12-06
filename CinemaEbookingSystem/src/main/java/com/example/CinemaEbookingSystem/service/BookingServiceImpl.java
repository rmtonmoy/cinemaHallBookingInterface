package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.model.Booking;
import com.example.CinemaEbookingSystem.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService{
    @Autowired
    BookingRepository bookingRepository;

    @Override
    public void save(Booking booking) {
        bookingRepository.save(booking);
    }
}
