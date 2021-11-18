package com.example.CinemaEbookingSystem.service;
import com.example.CinemaEbookingSystem.model.Theater;
import com.example.CinemaEbookingSystem.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterServiceImpl implements TheaterService{
    @Autowired
    TheaterRepository theaterRepository;

    @Override
    public Theater findById(Long Id) {
        List<Theater> theaterList = theaterRepository.findAll();
        for(Theater theater : theaterList){
            if(theater.getId() == Id){
                return  theater;
            }
        }

        return null;
    }
}
