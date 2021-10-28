package com.example.CinemaEbookingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.CinemaEbookingSystem.model.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    
    @Query(
        value = "SELECT id FROM customer c WHERE c.email = :customerEmail",
        nativeQuery = true)
    long findCustomerId(@Param("customerEmail") Customer customerEmail);
}
