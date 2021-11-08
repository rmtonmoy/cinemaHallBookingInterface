package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.model.User;
import com.example.CinemaEbookingSystem.service.EmailService;
import com.example.CinemaEbookingSystem.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Controller
public class EmailController {

    @Autowired
    EmailService emailService;

    

}
