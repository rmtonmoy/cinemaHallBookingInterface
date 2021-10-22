package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.model.MovieInfo;
import com.example.CinemaEbookingSystem.repository.MovieInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieInfoServiceImpl implements MovieInfoService{

    @Autowired
    private MovieInfoRepository MovieInfoRepository;

    @Override
    public List<MovieInfo> getAllMovieInfo(){
        return MovieInfoRepository.findAll();
    }

    @Override
    public void SaveMovieInfo(MovieInfo movieInfo)
    {
        this.MovieInfoRepository.save(movieInfo);
    }
}
