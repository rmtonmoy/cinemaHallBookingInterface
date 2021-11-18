package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.dto.MovieInfoDto;
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
    
    @Override
    public List<String> getCategories() {
        return MovieInfoRepository.getCategories();
    }

    @Override
    public boolean hasMovie(String title){
        List<MovieInfo> movieInfoList = getAllMovieInfo();
        for(MovieInfo movieInfo : movieInfoList){
            if(movieInfo.getTitle().equals(title) == true){
                return true;
            }
        }
        return  false;
    }

    @Override
    public MovieInfo findByTitle(String title) {
        List<MovieInfo> movieInfoList = getAllMovieInfo();
        for(MovieInfo movieInfo : movieInfoList){
            if(movieInfo.getTitle().equals(title) == true){
                return movieInfo;
            }
        }
        return null;
    }
}
