package com.example.CinemaEbookingSystem.repository;

import com.example.CinemaEbookingSystem.model.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> findAll();
}
