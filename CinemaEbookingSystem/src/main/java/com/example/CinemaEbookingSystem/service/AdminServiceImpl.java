package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.dto.PasswordDto;
import com.example.CinemaEbookingSystem.dto.UserRegistrationDto;
import com.example.CinemaEbookingSystem.model.Admin;
import com.example.CinemaEbookingSystem.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    EmailService emailService;

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public int save(UserRegistrationDto userRegistrationDto) {
        Admin foundAdmin = adminRepository.findByEmail(userRegistrationDto.getEmail());
        if(foundAdmin == null) {
            if((emailService.createVerificationCodeForAdmin(userRegistrationDto.getEmail())).equals(userRegistrationDto.getVcode())) {
                Admin admin = new Admin(userRegistrationDto.getFirstName(), userRegistrationDto.getLastName(),
                        userRegistrationDto.getPassword(), userRegistrationDto.getEmail(),
                        userRegistrationDto.getDob());

                adminRepository.save(admin);
                return 0;
            }
            else return 1;

        }
        else
            return 2;
    }

    @Override
    public boolean isCorrectPassword(String email, PasswordDto passwordDto) {
        Admin admin = adminRepository.findByEmail(email);

        if (passwordDto.getOldPassword().equals(admin.getPassword())) {
            admin.setPassword(passwordDto.getNewPassword());
            return true;
        } else {
            return false;
        }
    }
}
