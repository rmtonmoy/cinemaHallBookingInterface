package com.example.CinemaEbookingSystem.repository;

import com.example.CinemaEbookingSystem.model.PaymentCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.CinemaEbookingSystem.model.Customer;

import java.util.Collection;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    @Query(
            value = "SELECT * FROM customer WHERE email = ?1",
            nativeQuery = true)
    Customer findByEmail(String email);

}
