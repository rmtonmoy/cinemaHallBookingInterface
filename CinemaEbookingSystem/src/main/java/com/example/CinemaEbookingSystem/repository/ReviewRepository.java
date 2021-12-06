package com.example.CinemaEbookingSystem.repository;

import com.example.CinemaEbookingSystem.model.Promotion;
import com.example.CinemaEbookingSystem.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(
            value = "SELECT * FROM reviews WHERE fk_mid = ?1",
            nativeQuery = true)
    List<Review> findByMovieId(int id);
}
