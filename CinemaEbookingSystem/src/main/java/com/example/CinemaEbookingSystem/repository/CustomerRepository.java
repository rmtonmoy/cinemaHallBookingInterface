package com.example.CinemaEbookingSystem.repository;

import com.example.CinemaEbookingSystem.model.UserStatus;
import org.apache.catalina.util.CustomObjectInputStream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.CinemaEbookingSystem.model.Customer;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.CinemaEbookingSystem.model.Customer;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    @Query(
            value = "SELECT * FROM customer WHERE email = ?1",
            nativeQuery = true)
    Customer findByEmail(String email);

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE customer set status = 'Active' WHERE email = ?1", nativeQuery = true)
    void updateStatustoActive(String email);


    @Query(
        value = "SELECT id FROM customer WHERE email = ?1",
        nativeQuery = true)
    long findCustomerId(String customerEmail);
}
