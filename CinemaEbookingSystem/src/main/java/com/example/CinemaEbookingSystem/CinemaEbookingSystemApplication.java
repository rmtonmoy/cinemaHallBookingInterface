package com.example.CinemaEbookingSystem;

import com.example.CinemaEbookingSystem.service.MailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.example.CinemaEbookingSystem.model.Mail;
import com.example.CinemaEbookingSystem.model.MailConfiguration;
import com.example.CinemaEbookingSystem.service.MailService;
import com.example.CinemaEbookingSystem.service.MailServiceImpl;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})

public class CinemaEbookingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaEbookingSystemApplication.class, args);
    }

}