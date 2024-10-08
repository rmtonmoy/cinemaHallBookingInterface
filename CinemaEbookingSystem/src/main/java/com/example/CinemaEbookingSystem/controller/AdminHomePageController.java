package com.example.CinemaEbookingSystem.controller;

import com.example.CinemaEbookingSystem.dto.MovieDto;
import com.example.CinemaEbookingSystem.dto.TheaterDto;
import com.example.CinemaEbookingSystem.dto.SchedulerDto;
import com.example.CinemaEbookingSystem.model.*;
import com.example.CinemaEbookingSystem.dto.PasswordDto;
import com.example.CinemaEbookingSystem.model.Admin;
import com.example.CinemaEbookingSystem.model.MovieInfo;
import com.example.CinemaEbookingSystem.model.OneShow;
import com.example.CinemaEbookingSystem.model.Theater;
import com.example.CinemaEbookingSystem.repository.AdminRepository;
import com.example.CinemaEbookingSystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class AdminHomePageController {
    @Autowired
    PopulateDBService populateDBService;

    @Autowired
    EmailService emailService;

    @Autowired
    MovieInfoService movieInfoService;

    @Autowired
    OneShowService oneShowService;

    @Autowired
    TicketService ticketService;

    @Autowired
    TheaterService theaterService;

    @Autowired
    AdminService adminService;

    @Autowired
    AdminRepository adminRepository;

    @ModelAttribute("theaterDto")
    public TheaterDto theaterDto(){ return new TheaterDto();}

    @ModelAttribute("movieDto")
    public MovieDto movieDto(){ return new MovieDto();}

    @GetMapping(path = "/addTheater")
    public String showAllTheaters(Model model, HttpSession session){
        if (adminRepository.findByEmail(session.getAttribute("email").toString()) != null) {
            List<Theater> theaterList = theaterService.getAllTheaters();
            
            model.addAttribute("userName", session.getAttribute("name"));
            model.addAttribute("email", session.getAttribute("email"));
            model.addAttribute("listTheater", theaterList);
            return "addTheater";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping("/addNewAdmin")
    public String addNewAdmin(Model model, @RequestParam String adminEmail) {
        emailService.sendAdminRequest(adminEmail);
        return "redirect:/adminHome";
    }

    @PostMapping(path = "/addTheater")
    public String addTheater(@ModelAttribute("theaterDto") TheaterDto theaterDto){
        theaterService.addTheater(theaterDto.getMaxR(), theaterDto.getMaxC());
        return "redirect:/addTheater";
    }

    @GetMapping(path = "/manageMovies")
    public String showAllMovies(Model model, HttpSession session){
        if (adminRepository.findByEmail(session.getAttribute("email").toString()) != null) {
            List<MovieInfo> MovieList = movieInfoService.getAllMovieInfo();
            List<MovieInfo> listCurrentMovie = movieInfoService.listOfCurrentMovies();
            List<MovieInfo> listComingSoonMovie = movieInfoService.listOfComingSoonMovies();
    
            model.addAttribute("userName", session.getAttribute("name"));
            model.addAttribute("email", session.getAttribute("email"));
            model.addAttribute("listCurrentMovie",listCurrentMovie);
            model.addAttribute("listComingSoonMovie",listComingSoonMovie);
            model.addAttribute("listMovie", MovieList);
            return "manageMovies";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping(path = "/manageMoviesDto")
    public String saveMovie(@ModelAttribute("movieDto") MovieDto movieDto){
        movieInfoService.SaveMovieInfoWithDto(movieDto);
        return "redirect:/manageMovies";
    }

    @GetMapping(path = "/scheduleMovie/{id}")
    public String scheduleMovie(@PathVariable (value = "id") int id, Model model,HttpSession session){
        if (adminRepository.findByEmail(session.getAttribute("email").toString()) != null) {
            model.addAttribute("userName", session.getAttribute("name"));
            model.addAttribute("email", session.getAttribute("email"));
            model.addAttribute("oneShowList", ticketService.generateSchedule(id));
            model.addAttribute("movieTitle", movieInfoService.findById(id).getTitle());
            return "scheduleMovie";
        } else {
            return "redirect:/";
        }
    }


    @ModelAttribute("schedulerDto")
    public SchedulerDto schedulerDto(){ return new SchedulerDto();}

    @PostMapping(path = "/scheduleMovie")
    public String scheduleMovie(Model model, @ModelAttribute("schedulerDto") SchedulerDto schedulerDto){
        Date date = schedulerDto.getDate();
        long theaterId = schedulerDto.getTheaterId();
        int startingAt = schedulerDto.getStartingTimeInMinutes();
        String movieName = schedulerDto.getMovieTitle();

        if(movieInfoService.hasMovie(movieName)){
            OneShow oneShow = oneShowService.save(date, theaterId, startingAt, movieName);
            boolean canAdd = ticketService.addTickets(oneShow);

            if(canAdd){
                return "redirect:/manageMovies?SchedulerSuccess";
            }
            else{
                return "redirect:/manageMovies?TimeConflict";
            }
        }
        else{
            return "redirect:/manageMovies?MovieNotFound";
        }
    }

    @RequestMapping(value = "assignMovie", method = RequestMethod.GET)
    void assignMovie(Model model, @RequestParam("date") Date date, @RequestParam("theaterId") long theaterId,
                     @RequestParam("startingAt") int startingAt, @RequestParam("movieName") String movieName)
    {
        if(movieInfoService.hasMovie(movieName)){
            OneShow oneShow = oneShowService.save(date, theaterId, startingAt, movieName);
            boolean canAdd = ticketService.addTickets(oneShow);

            if(canAdd){
                model.addAttribute("verdict", "Tickets added!");
            }
            else{
                model.addAttribute("verdict", "Time Conflict!");
            }
        }
        else{
            model.addAttribute("verdict", "Movie Not Found!");
        }
    }

    @GetMapping(path = "/adminHome")
    String adminHome(Model model, HttpSession session) {
        if (adminRepository.findByEmail(session.getAttribute("email").toString()) != null) {
            // System.out.println(session.getAttribute("email"));
            // System.out.println(session.getAttribute("name"));
            model.addAttribute("something", "Cinema E-booking System");
            model.addAttribute("userName", session.getAttribute("name"));
            model.addAttribute("email", session.getAttribute("email"));
            return "adminHome";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping(path = "/seeSchedule")
    String seeSchedule(Model model, HttpSession session){

        model.addAttribute("userName", session.getAttribute("name"));
        model.addAttribute("email", session.getAttribute("email"));
        List<List<String>> scheduleTable = ticketService.generateSchedule();
        model.addAttribute("scheduleTable", scheduleTable);

        for(List<String> row : scheduleTable){
            for(String entry: row){
                System.out.println(entry + " - ");
            }
            System.out.println("");
        }
        return "seeSchedule";
    }


    @ModelAttribute("ticketP")
    public TicketPrice ticketPrice(){ return new TicketPrice();}

    @GetMapping(path = "/manageTickets")
    public String showAllTicketPrice(Model model, HttpSession session) {
        if (adminRepository.findByEmail(session.getAttribute("email").toString()) != null) {
            List<TicketPrice> ticketPrice = ticketService.allTicketPrice();
            model.addAttribute("userName", session.getAttribute("name"));
            model.addAttribute("email", session.getAttribute("email"));
            model.addAttribute("ListofTicketPrice", ticketPrice);
            model.addAttribute("BookingFee",ticketService.getBookingFee());
            return "manageTickets";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping(path = "/manageTickets")
    public String saveTicketPrice(@ModelAttribute("ticketP") TicketPrice ticketPrice) {
        ticketService.savePrice(ticketPrice.getTypeOfTicket(),ticketPrice.getPrice());
        ticketService.updateBookingFeeForAll(ticketPrice.getBookingFee());
        return "redirect:/manageTickets";
    }
    
    @GetMapping(path = "/adminHome/editProfile")
    public String adminEditProfile(Model model, HttpSession session) {
        if (adminRepository.findByEmail(session.getAttribute("email").toString()) != null) {
            model.addAttribute("email", session.getAttribute("email"));
            model.addAttribute("userName", session.getAttribute("name"));
        
            Admin admin = adminRepository.findByEmail(session.getAttribute("email").toString());
        
            model.addAttribute("admin", admin);
            return "adminEditProfile";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping(path = "/saveAdminInfo")
    public String saveAdminInfo(@ModelAttribute("admin") Admin admin) {
       
        // Save/update admin info in database
        adminRepository.save(admin);
        return "redirect:/adminHome/editProfile?SuccessInfo";
    }

    @GetMapping(path = "/adminHome/changePassword")
    public String changePassword(Model model, HttpSession session) {
        if (adminRepository.findByEmail(session.getAttribute("email").toString()) != null) {
            model.addAttribute("email", session.getAttribute("email"));
            model.addAttribute("userName", session.getAttribute("name"));
            model.addAttribute("passwordDto", new PasswordDto());
        
            return "adminChangePassword";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping(path = "/adminHome/changePassword")
    public String changePassword(@ModelAttribute("passwordDto") PasswordDto passwordDto, HttpSession session) {
        String adminEmail = session.getAttribute("email").toString();
        
        if (!(adminService.isCorrectPassword(adminEmail, passwordDto))) {
            return "redirect:/adminHome/changePassword?IncorrectPassword";
        } else {
            return "redirect:/adminHome/editProfile?SuccessPassword";
        }
    }
}
