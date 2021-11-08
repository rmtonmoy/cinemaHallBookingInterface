package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.model.User;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    String password = "salehpass123";

    public EmailServiceImpl() {
    }

    public EmailServiceImpl(String password) {
        this.password = password;
    }

    public String createVerificationCode(String password) {
        String shortPassword = password.substring(0,5);
        String verificationCode = "";

        try {
            byte[] bytes = shortPassword.getBytes("US-ASCII");
            verificationCode = Arrays.toString(bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return verificationCode;
    }

    //send email to the user email
    public boolean sendEmail(User user) {
        boolean test = false;

        String toEmail = "easonsusan140@gmail.com";
        String fromEmail = "se.projectgroupc4@gmail.com";
        String password = "salehpass123";

        try {
            // your host email smtp server details
            Properties pr = new Properties();
            pr.setProperty("mail.smtp.host", "smtp.mail.com");
            pr.setProperty("mail.smtp.port", "587");
            pr.setProperty("mail.smtp.auth", "true");
            pr.setProperty("mail.smtp.starttls.enable", "true");
            pr.put("mail.smtp.socketFactory.port", "587");
            pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            //get session to authenticate the host email address and password
            Session session = Session.getInstance(pr, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            //set email message details
            Message mess = new MimeMessage(session);

            //set from email address
            mess.setFrom(new InternetAddress(fromEmail));
            //set to email address or destination email address
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

            //set email subject
            mess.setSubject("User Email Verification");

            //set message text
            mess.setText("Registered successfully.Please verify your account using this code: " + createVerificationCode(password));
            //send the message
            Transport.send(mess);
            test = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return test;
    }
}
