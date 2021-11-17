package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.model.MovieInfo;
import com.example.CinemaEbookingSystem.model.Theater;
import com.example.CinemaEbookingSystem.repository.MovieInfoRepository;
import com.example.CinemaEbookingSystem.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;

@Service
public class PopulateDBServiceImpl implements PopulateDBService{

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private MovieInfoRepository movieInfoRepository;

    @Override
    public void addTheater(int maxR, int maxC){
        Theater theater = new Theater(maxR, maxC);
        theaterRepository.save(theater);
    }

}
