package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.dto.VerificationDto;
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

    public void sendEmail(String toEmail){

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

    //send email to the user email
//    public boolean sendEmail(User user) {
//        boolean test = false;
//
//        String toEmail = "easonsusan140@gmail.com";
//        String fromEmail = "se.projectgroupc4@gmail.com";
//        String password = "salehpass123";
//
//        try {
//            // your host email smtp server details
//            Properties pr = new Properties();
//            pr.setProperty("mail.smtp.host", "smtp.mail.com");
//            pr.setProperty("mail.smtp.port", "587");
//            pr.setProperty("mail.smtp.auth", "true");
//            pr.setProperty("mail.smtp.starttls.enable", "true");
//            pr.put("mail.smtp.socketFactory.port", "587");
//            pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//
//            //get session to authenticate the host email address and password
//            Session session = Session.getInstance(pr, new Authenticator() {
//                @Override
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(fromEmail, password);
//                }
//            });
//
//            //set email message details
//            Message mess = new MimeMessage(session);
//
//            //set from email address
//            mess.setFrom(new InternetAddress(fromEmail));
//            //set to email address or destination email address
//            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
//
//            //set email subject
//            mess.setSubject("User Email Verification");
//
//            //set message text
//            mess.setText("Registered successfully.Please verify your account using this code: " + createVerificationCode(password));
//            //send the message
//            Transport.send(mess);
//            test = true;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return test;
//    }
}
