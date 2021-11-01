package com.example.CinemaEbookingSystem.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Table
@Entity
public class Admin extends User{

    public Admin(){

    }

    public Admin(String firstName, String lastName, String password, String email, String dob) {
        super(firstName, lastName, password, email, dob);
    }
}
