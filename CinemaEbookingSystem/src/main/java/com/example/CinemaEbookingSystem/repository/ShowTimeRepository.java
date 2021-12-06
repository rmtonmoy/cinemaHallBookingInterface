package com.example.CinemaEbookingSystem.repository;

import com.example.CinemaEbookingSystem.model.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTime, Long> {
}
