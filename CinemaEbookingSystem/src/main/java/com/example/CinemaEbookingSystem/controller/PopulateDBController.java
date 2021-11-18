package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.service.PopulateDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class PopulateDBController {

    @Autowired
    PopulateDBService populateDBService;

    @RequestMapping(value = "addTheater", method = RequestMethod.GET)
    void addSeat(@RequestParam("maxR") int maxR, @RequestParam("maxC") int maxC){
        populateDBService.addTheater(maxR, maxC);
    }
}
