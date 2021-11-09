package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.model.Mail;
import com.example.CinemaEbookingSystem.service.MailService;
import com.example.CinemaEbookingSystem.service.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class EmailController {

    @Autowired
    Mail mail = new Mail();
    mail.setMailFrom("yashwantchavan@gmail.com");
    mail.setMailTo("yashwantchavan@gmail.com");
    mail.setMailSubject("Spring Boot - Email Example");
    mail.setMailContent("Learn How to send Email using Spring Boot!!!\n\nThanks\nwww.technicalkeeda.com");

    @Autowired
    MailService mailService;
    mailService.sendEmail(mail);
    

}
