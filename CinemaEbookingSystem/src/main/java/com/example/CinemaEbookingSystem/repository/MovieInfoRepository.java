package com.example.CinemaEbookingSystem.repository;

import com.example.CinemaEbookingSystem.model.MovieInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieInfoRepository extends JpaRepository<MovieInfo, Integer> {

}
