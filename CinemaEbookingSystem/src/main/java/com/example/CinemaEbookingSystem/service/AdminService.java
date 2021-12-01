package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.dto.PaymentCardDto;
import com.example.CinemaEbookingSystem.dto.UserRegistrationDto;
import com.example.CinemaEbookingSystem.model.Admin;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AdminService {
    List<Admin> getAllAdmins();
    boolean save(UserRegistrationDto userRegistrationDto);
}
