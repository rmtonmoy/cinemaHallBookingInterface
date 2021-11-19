package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.dto.MovieDto;
import com.example.CinemaEbookingSystem.model.MovieInfo;
import com.example.CinemaEbookingSystem.model.OneShow;
import com.example.CinemaEbookingSystem.repository.MovieInfoRepository;
import com.example.CinemaEbookingSystem.repository.OneShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieInfoServiceImpl implements MovieInfoService{

    @Autowired
    private MovieInfoRepository movieInfoRepository;

    @Autowired
    private OneShowRepository oneShowRepository;

    @Override
    public List<MovieInfo> getAllMovieInfo(){
        return movieInfoRepository.findAll();
    }

    @Override
    public void SaveMovieInfo(MovieInfo movieInfo){
        this.movieInfoRepository.save(movieInfo);
    }

    @Override
    public void SaveMovieInfoWithDto(MovieDto movieDto){
        MovieInfo movieInfo = new MovieInfo(movieDto.getTitle(),movieDto.getCategory(), movieDto.getCast(),
                movieDto.getDirector(),movieDto.getProducer(), movieDto.getSynopsis(),movieDto.getMPAA_rating(),
                movieDto.getDuration(),movieDto.getLinkToPoster(),movieDto.getLinkToTrailer());
        this.movieInfoRepository.save(movieInfo);
    }
    
    @Override
    public List<String> getCategories() {
        return movieInfoRepository.getCategories();
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
    
    @Override
    public MovieInfo findById(int id) {
        return movieInfoRepository.getMovieById(id);
    }

    @Override
    public boolean hasBeenScheduled(int id) {
        List<OneShow> oneShowList = oneShowRepository.findAll();
        for(OneShow oneShow : oneShowList){
            if(oneShow.getMovieInfo().getId() == id){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<MovieInfo> listOfCurrentMovies() {
        List<MovieInfo> ret = new ArrayList<>();
        List<MovieInfo> movieInfoList = movieInfoRepository.findAll();
        for(MovieInfo movieInfo : movieInfoList){
            if(hasBeenScheduled(movieInfo.getId())){
                ret.add(movieInfo);
            }
        }
        return ret;
    }

    @Override
    public List<MovieInfo> listOfComingSoonMovies() {
        List<MovieInfo> ret = new ArrayList<>();
        List<MovieInfo> movieInfoList = movieInfoRepository.findAll();
        for(MovieInfo movieInfo : movieInfoList){
            if(hasBeenScheduled(movieInfo.getId()) == false){
                ret.add(movieInfo);
            }
        }
        return ret;
    }


}
