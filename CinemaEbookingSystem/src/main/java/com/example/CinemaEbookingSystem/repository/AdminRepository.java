package com.example.CinemaEbookingSystem.repository;

import com.example.CinemaEbookingSystem.model.Admin;
import com.example.CinemaEbookingSystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Query(
            value = "SELECT * FROM admin WHERE email = ?1",
            nativeQuery = true)
    Admin findByEmail(String email);
}
