package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.dto.MovieSearchDto;
import com.example.CinemaEbookingSystem.model.MovieInfo;
import com.example.CinemaEbookingSystem.service.MovieInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.CinemaEbookingSystem.model.Admin;
import com.example.CinemaEbookingSystem.model.OneShow;
import com.example.CinemaEbookingSystem.repository.AdminRepository;
import com.example.CinemaEbookingSystem.service.OneShowService;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.Arrays;
import java.util.Date;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieController {
    
    @Autowired
    MovieInfoService movieInfoService;
    
    @Autowired
    OneShowService oneShowService;
    
    public MovieController(MovieInfoService mis, OneShowService oss) {
        super();
        movieInfoService = mis;
        oneShowService   = oss;
    }

    @GetMapping(path = "/")
    String homepage(Model model, HttpSession session){
        System.out.println(session.getAttribute("email"));
        System.out.println(session.getAttribute("name"));
        model.addAttribute("something", "Cinema E-booking System");
        model.addAttribute("userName", session.getAttribute("name"));
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("listCurrentMovie", movieInfoService.listOfCurrentMovies());
        model.addAttribute("listComingSoonMovie", movieInfoService.listOfComingSoonMovies());
        return "home";
    }

    @GetMapping(path = "/paymentConfirmation")
    String paymentConfirmation(Model model, HttpSession session){
        model.addAttribute("something", "Cinema E-booking System");
        return "paymentConfirmation";
    }

    // @GetMapping(path = "/signup")
    //String signUp(Model model){
    //    return "signup";
    //}

    @GetMapping(path = "/registration_confirmation")
    String registrationConfirmationMessage(Model model, HttpSession session){
        return "registrationconfirmation";
    }

    @GetMapping(path = "/orderHistory")
    String getOrderHistory(Model model, HttpSession session) {
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("userName", session.getAttribute("name"));
        return "Order-History";
    }

    @GetMapping(path = "/orderSummary")
    String orderSummary(Model model, HttpSession session){
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("userName", session.getAttribute("name"));
        model.addAttribute("something", "Cinema E-booking System");
        return "orderSummary";
    }
    
    @GetMapping(path = "/movie")
    String getMovie(Model model, HttpSession session,
        @RequestParam(name = "id", required = false) int id) {
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("userName", session.getAttribute("name"));
        
        MovieInfo movie = movieInfoService.findById(id);
        if (movie != null) {
            model.addAttribute("movie", movie);
            //String trailerLink = movie.getLinkToTrailer();
            //model.addAttribute("youtubeId", trailerLink.substring(trailerLink.indexOf("v=") + 2));
            model.addAttribute("showings", oneShowService.getAllShowsForMovie(id));
        } else {
            return "redirect:/search";
        }
        
        return "movie";
    }
    
    // =================================================================================================================
    // Search & filter
    
    private boolean isSameDay(Date a, Date b) {
        return a.getYear()  == b.getYear()
            && a.getMonth() == b.getMonth()
            && a.getDate()  == b.getDate();
    }
    
    @GetMapping(path = "/search")
    String getSearch(Model model, HttpSession session, @ModelAttribute("searchParams") MovieSearchDto searchParams) {
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("userName", session.getAttribute("name"));
        
        List<String> categories = movieInfoService.getCategories();
        categories.add(0, "Any");
        model.addAttribute("categories", categories);
        
        // TODO: Cooler title match algorithm
        List<MovieInfo> movies   = movieInfoService.getAllMovieInfo();
        List<MovieInfo> filtered = new ArrayList(); // ughhhh the memory inefficiencies!!!
        for (MovieInfo info : movies) {
            boolean include = true;
            if (!(searchParams.title == null || searchParams.title.equals("") || searchParams.title.equals("*"))) {
                if (!info.getTitle().toUpperCase().contains(searchParams.title.toUpperCase()))
                    include = false;
            }
            if (!(searchParams.cat == null || searchParams.cat.equals("") || searchParams.cat.equals("Any"))) {
                if (!info.getCategory().equalsIgnoreCase(searchParams.cat))
                    include = false;
            }
            if (!(searchParams.status == null || searchParams.status.equals("") || searchParams.status.equals("any"))) {
                if (searchParams.status.equals("showing") && oneShowService.getAllShowsForMovie(info.getId()).isEmpty()) {
                    include = false;
                } else if (searchParams.status.equals("soon") && !oneShowService.getAllShowsForMovie(info.getId()).isEmpty()) {
                    include = false;
                }
            }
            if (searchParams.getDate() != null) {
                boolean hasShowing = false;
                for (OneShow show : oneShowService.getAllShowsForMovie(info.getId())) {
                    if (isSameDay(searchParams.getDate(), show.getShowTime().getDate())) {
                        hasShowing = true;
                        break;
                    }
                }
                if (!hasShowing) {
                    include = false;
                }
            }
            
            if (include)
                filtered.add(info);
        }
        model.addAttribute("movies", filtered);
        return "search";
    }

}