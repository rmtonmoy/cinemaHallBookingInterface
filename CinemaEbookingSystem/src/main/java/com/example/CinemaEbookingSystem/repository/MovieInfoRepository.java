package com.example.CinemaEbookingSystem.repository;

import com.example.CinemaEbookingSystem.model.MovieInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface MovieInfoRepository extends JpaRepository<MovieInfo, Integer> {

    @Override
    @Query(
        value = "SELECT * FROM movie_info",
        nativeQuery = true
    )
    List<MovieInfo> findAll();
    
    @Query(
        value = "SELECT DISTINCT category FROM movie_info",
        nativeQuery = true
    )
    List<String> getCategories();
    
    @Query(
        value = "SELECT * FROM movie_info WHERE id = :id",
        nativeQuery = true
    )
    MovieInfo getMovieById(@Param("id") int id);
    
}
