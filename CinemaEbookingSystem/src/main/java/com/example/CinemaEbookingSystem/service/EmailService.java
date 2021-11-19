package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.dto.PasswordAndVerificationDto;
import com.example.CinemaEbookingSystem.dto.VerificationDto;
import com.example.CinemaEbookingSystem.model.Promotion;
import com.example.CinemaEbookingSystem.model.User;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
public interface EmailService {
    public void sendVerificationEmail(String toEmail);
    public String createVerificationCode(String email);
    boolean verifyCustomer(VerificationDto verificationDto);
    public void sendPromotionalEmail(String toEmail, Promotion promotion);
    boolean sendEmailRP(String toEmail);
    String createVerificationCode2(String email);
    boolean verifyCustomerRP(PasswordAndVerificationDto passwordAndVerificationDto);
    boolean confirmPassword(PasswordAndVerificationDto passwordAndVerificationDto);
    void resetPassword(PasswordAndVerificationDto passwordAndVerificationDto);

}
