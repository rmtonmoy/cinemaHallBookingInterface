package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.dto.UserRegistrationDto;
import com.example.CinemaEbookingSystem.model.Admin;
import com.example.CinemaEbookingSystem.model.Customer;
import com.example.CinemaEbookingSystem.model.PaymentCard;
import com.example.CinemaEbookingSystem.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminRepository adminRepository;

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public boolean save(UserRegistrationDto userRegistrationDto) {
        Admin foundAdmin = adminRepository.findByEmail(userRegistrationDto.getEmail());
        if(foundAdmin == null) {
            Admin admin = new Admin(userRegistrationDto.getFirstName(), userRegistrationDto.getLastName(),
                    userRegistrationDto.getPassword(), userRegistrationDto.getEmail(),
                    userRegistrationDto.getDob());

            adminRepository.save(admin);
            return true;
        }
        else
            return false;
    }

}
