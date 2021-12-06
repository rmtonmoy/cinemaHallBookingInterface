package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.dto.CheckoutDto;
import com.example.CinemaEbookingSystem.dto.MovieSearchDto;
import com.example.CinemaEbookingSystem.dto.ReviewDto;
import com.example.CinemaEbookingSystem.model.*;
import com.example.CinemaEbookingSystem.repository.ReviewRepository;
import com.example.CinemaEbookingSystem.service.MovieInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.CinemaEbookingSystem.repository.AdminRepository;
import com.example.CinemaEbookingSystem.service.OneShowService;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Date;

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

    // @GetMapping(path = "/signup")
    //String signUp(Model model){
    //    return "signup";
    //}

    @GetMapping(path = "/registration_confirmation")
    String registrationConfirmationMessage(Model model, HttpSession session){
        return "registrationconfirmation";
    }

    @GetMapping(path = "/orderSummary")
    String orderSummary(Model model, HttpSession session){
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("userName", session.getAttribute("name"));
        model.addAttribute("something", "Cinema E-booking System");
        return "orderSummary";
    }
    @Autowired
    ReviewRepository reviewRepository;

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
            List<Review> reviews = reviewRepository.findByMovieId(id);
            model.addAttribute("reviews", reviews);

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
            if (searchParams.date != null) {
                boolean hasShowing = false;
                for (OneShow show : oneShowService.getAllShowsForMovie(info.getId())) {
                    if (isSameDay(searchParams.date, show.getShowTime().getDate())) {
                        hasShowing = true;
                        break;
                    }
                }
                if (!hasShowing) {
                    include = false;
                } else {
                    // TODO display show dates if date-less search; show times as well on dated searches
                }
            }
            
            if (include)
                filtered.add(info);
        }
        if (searchParams.date != null) {
            model.addAttribute("date", searchParams.date);
        }
        model.addAttribute("OSS", oneShowService);
        model.addAttribute("movies", filtered);
        return "search";
    }
    @ModelAttribute("reviewDto")
    public ReviewDto reviewdto(){ return new ReviewDto();}

    @PostMapping(path = "/addReview/{id}")
    public String addReview(@PathVariable(value = "id") int id, @ModelAttribute("reviewDto") ReviewDto reviewdto, HttpSession session){
        String email = (String) session.getAttribute("email");
        MovieInfo movieInfo = movieInfoService.findById(id);
        Review review = new Review(reviewdto.getReview(),movieInfo);
        System.out.print("-> Name = " + id);
        movieInfoService.saveReview(review);
        return "redirect:/movie?id="+id;
    }


}