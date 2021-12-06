package com.example.CinemaEbookingSystem.repository;

import com.example.CinemaEbookingSystem.model.OneShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OneShowRepository extends JpaRepository<OneShow, Long> {

}
