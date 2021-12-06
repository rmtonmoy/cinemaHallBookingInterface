package com.example.CinemaEbookingSystem.dto;

import java.util.Base64;

public class VerificationDto {
    private String email;
    private String vcode;

    public VerificationDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public String encodeEmail(String email) {
        String encodedEmail = new String(Base64.getEncoder().encodeToString(email.getBytes()));
        return encodedEmail;
    }
}
