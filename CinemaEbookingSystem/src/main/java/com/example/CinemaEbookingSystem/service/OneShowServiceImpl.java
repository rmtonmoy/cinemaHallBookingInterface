package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.model.MovieInfo;
import com.example.CinemaEbookingSystem.model.OneShow;
import com.example.CinemaEbookingSystem.model.ShowTime;
import com.example.CinemaEbookingSystem.model.Theater;
import com.example.CinemaEbookingSystem.repository.OneShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OneShowServiceImpl implements OneShowService{
    @Autowired
    ShowTimeService showTimeService;

    @Autowired
    TheaterService theaterService;

    @Autowired
    MovieInfoService movieInfoService;

    @Autowired
    OneShowRepository oneShowRepository;

    /*
        To-do:
        - Check the validity of theaterID
     */
    @Override
    public OneShow save(String date, Long theaterId, int startingAt, String movieName) {
        ShowTime showTime = showTimeService.saveIfNotFound(date, startingAt);
        MovieInfo movieInfo = movieInfoService.findByTitle(movieName);
        Theater theater = theaterService.findById(theaterId);
        OneShow oneShow = new OneShow(showTime, theater, movieInfo);


        List<OneShow> oneShowList = oneShowRepository.findAll(); 
        for(OneShow anotherOneShow : oneShowList){
            if(anotherOneShow.getShowTime().getId() == oneShow.getShowTime().getId()
                && anotherOneShow.getMovieInfo().getId() == oneShow.getMovieInfo().getId()
                && anotherOneShow.getTheater().getId() == oneShow.getTheater().getId()){
                return anotherOneShow;
            }
        }

        oneShowRepository.save(oneShow);
        return oneShow;
    }
}
