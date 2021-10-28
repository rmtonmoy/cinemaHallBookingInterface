package com.example.CinemaEbookingSystem.controller;

import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SignOutController {

    @GetMapping(path = "/logout")
    String logout(Model model, HttpServletRequest request){
        request.getSession().removeAttribute("email");
        return "movie";
    }
}
