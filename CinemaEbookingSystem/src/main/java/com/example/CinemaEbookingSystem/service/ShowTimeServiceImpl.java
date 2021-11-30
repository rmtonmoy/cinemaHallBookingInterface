package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.model.ShowTime;
import com.example.CinemaEbookingSystem.repository.ShowTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShowTimeServiceImpl implements ShowTimeService{
    @Autowired
    ShowTimeRepository showTimeRepository;

    @Override
    public ShowTime saveIfNotFound(Date date, int startingAt) {
        List<ShowTime> showTimeList = showTimeRepository.findAll();

        for(ShowTime showTime : showTimeList){
            if(showTime.getDate().equals(date) && showTime.getStartingTime() == startingAt){
                return showTime;
            }
        }

        ShowTime showTime = new ShowTime(date, startingAt);
        showTimeRepository.save(showTime);
        return  showTime;
    }
}
