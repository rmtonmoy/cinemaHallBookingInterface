package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.dto.VerificationDto;
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
    public void sendEmail(String toEmail);
    public String createVerificationCode(String email);
    boolean verifyCustomer(VerificationDto verificationDto);
}
