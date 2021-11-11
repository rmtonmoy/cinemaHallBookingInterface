package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.dto.VerificationDto;
import com.example.CinemaEbookingSystem.model.Promotion;
import com.example.CinemaEbookingSystem.model.User;
import com.example.CinemaEbookingSystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    //String password = "salehpass123";
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String toEmail){

        String subject = "User Email Verification";
        String verificationCode = createVerificationCode(toEmail);
        String body = " Your verification code is "+ verificationCode + ". Please verify on this link: http://localhost:8080/verify.";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("se.projectgroupc4@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);

        System.out.println("Mail sent successfully\n");
    }
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }



    public String createVerificationCode(String email) {
        String shortPassword = email.substring(0, 5);
        String verificationCode = "";
        StringBuilder sb = new StringBuilder();
        char[] letters = shortPassword.toCharArray();
        for (char ch : letters) {
            sb.append((byte) ch);
        }
        verificationCode = sb.toString();

        return verificationCode;
    }

    public boolean verifyCustomer(VerificationDto verificationDto)
    {
        String email = verificationDto.getEmail();
        String vcode = verificationDto.getVcode();
        if(vcode.equals(createVerificationCode(email))){
            customerRepository.updateStatustoActive(email);
            return true;
        }
        else{
            return false;
        }
    }

    public void sendPromotionalEmail(String toEmail, Promotion promotion) {

        String subject = promotion.getPromoTitle();
        String body = promotion.getPromoDescription();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("se.projectgroupc4@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);

        System.out.println("Mail sent successfully\n");

    }

}
