package com.example.CinemaEbookingSystem.dto;

import com.example.CinemaEbookingSystem.model.UserStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String dob;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private boolean isRegisteredForPromo;


    public UserRegistrationDto(){
        this.status = UserStatus.Inactive;
        this.isRegisteredForPromo = false;
    }

    public UserRegistrationDto(String firstName, String lastName, String password, String email, String dob, boolean isRegisteredForPromo) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.status = UserStatus.Inactive;
        this.isRegisteredForPromo = isRegisteredForPromo;
    }

    public UserStatus getStatus() {
        return status;
    }
    public void setStatus(UserStatus status) {
        this.status = status;
    }
    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isRegisteredForPromo() { return isRegisteredForPromo; }
    public void setRegisteredForPromo(boolean registeredForPromo) { isRegisteredForPromo = registeredForPromo; }
}
