package com.example.CinemaEbookingSystem.dto;

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
}
