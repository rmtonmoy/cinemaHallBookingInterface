package com.example.CinemaEbookingSystem.service;

import com.example.CinemaEbookingSystem.controller.SignOutController;
import com.example.CinemaEbookingSystem.model.*;
import com.example.CinemaEbookingSystem.repository.OneShowRepository;
import com.example.CinemaEbookingSystem.repository.TicketPriceRepository;
import com.example.CinemaEbookingSystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Arrays;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    MovieInfoService movieInfoService;

    @Autowired
    OneShowRepository oneShowRepository;

    public boolean hasTimeConflict(OneShow oneShow){
        MovieInfo movieInfo = oneShow.getMovieInfo();
        List<Ticket> ticketList = ticketRepository.findAll();

        int startB = oneShow.getShowTime().getStartingTime();
        int endB = startB + oneShow.getMovieInfo().getDuration();

        for(Ticket ticket: ticketList){
            if(ticket.getShow().getShowTime().getDateStringWithYear().equals(oneShow.getShowTime().getDateStringWithYear()) == false){
                continue;
            }

            if(ticket.getShow().getTheater().getId() != oneShow.getTheater().getId()){
                continue;
            }

            int startA = (ticket.getShow()).getShowTime().getStartingTime();
            int endA = startA + ticket.getShow().getMovieInfo().getDuration();

            int maxStart = Math.max(startA, startB);
            int minEnd = Math.min(endA, endB);

            if(maxStart <= minEnd){
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean addTickets(OneShow oneShow) {
        if(hasTimeConflict(oneShow)){
            return false;
        }
        Theater theater = oneShow.getTheater();
        List<Ticket> ticketList = ticketRepository.findAll();

        for(int row = 1; row <= theater.getMaxR(); row++){
            for(int column = 1; column <= theater.getMaxC(); column++){
                Ticket ticket = new Ticket();
                ticket.setPromotion(null);
                ticket.setPurchased(false);
                ticket.setTicketType(null);
                ticket.setTicketRn(row);
                ticket.setTicketCn(column);
                ticket.setShow(oneShow);

                /*boolean alreadyExists = false;
                for(Ticket anotherTicket : ticketList){
                    if( (anotherTicket.getShow()).getId() == oneShow.getId()
                        && anotherTicket.getTicketCn() == ticket.getTicketCn()
                        && anotherTicket.getTicketRn() == ticket.getTicketRn()){
                        alreadyExists = true;
                        break;
                    }
                }

                if(alreadyExists == false){
                    ticketRepository.save(ticket);
                }*/

                ticketRepository.save(ticket);
            }
        }

        return true;
    }

    //Designed for users
    @Override
    public List<List<String>> generateSchedule() {
        List<List<String>> scheduleTable = new ArrayList<List<String>>();
        List<String> headers = new ArrayList<>();

        Calendar today = Calendar.getInstance();
        List<Integer> importantTimes = new ArrayList<>();
        List<Ticket> ticketList = ticketRepository.findAll();
        headers.add("Date/Time");

        for(int i = 1; i <= 7; i++){
            Integer day = today.get(Calendar.DAY_OF_MONTH);
            Integer month = today.get(Calendar.MONTH) + 1;
            Integer year = today.get(Calendar.YEAR);

            String formatedDate = month.toString() + "/" + day.toString() + "/" + year.toString();
            for(Ticket ticket : ticketList){
                OneShow oneShow = ticket.getShow();
                ShowTime showTime = oneShow.getShowTime();
                Date varDate = showTime.getDate();
                String date = (varDate.getMonth()+1) + "/" + varDate.getDate() + "/" + (varDate.getYear()+1900);
                if(date.equals(formatedDate)){
                    importantTimes.add(showTime.getStartingTime());
                }
            }

            headers.add(formatedDate);
            today.add(Calendar.DATE, 1);
        }
        for(int i = 1; i <= 7; i++){
            today.add(Calendar.DATE, -1);
        }

        scheduleTable.add(headers);
        Set<Integer> setOfImportantTimes = new HashSet<>(importantTimes);
        importantTimes = new ArrayList<>(setOfImportantTimes);

        // Sad life - couldn't use java to sort so had to write my own sort
        for(int i = 0; i < importantTimes.size(); i++){
            for(int j = 0; j + 1 < importantTimes.size(); j++){
                if(importantTimes.get(j) > importantTimes.get(j+1)){
                    int temp = importantTimes.get(j);
                    importantTimes.set(j, importantTimes.get(j + 1));
                    importantTimes.set(j + 1, temp);
                }
            }
        }


        for(Integer particularStartingTime : importantTimes){
            ShowTime dummyShowTime = new ShowTime();
            dummyShowTime.setStartingTime(particularStartingTime);
            String formatedStartingTime = dummyShowTime.getStartingTimeString();

            List<String> currentRow = new ArrayList<>();
            currentRow.add(formatedStartingTime);

            //Debug dump
            System.out.println("->" + particularStartingTime);

            for(int i = 1; i <= 7; i++){
                Integer day = today.get(Calendar.DAY_OF_MONTH);
                Integer month = today.get(Calendar.MONTH) + 1;
                Integer year = today.get(Calendar.YEAR);
                String entry = "";
                Set<String> movieSet = new HashSet<>();

                String formatedDate = month.toString() + "/" + day.toString() + "/" + year.toString();
                for(Ticket ticket : ticketList){
                    OneShow oneShow = ticket.getShow();
                    ShowTime showTime = oneShow.getShowTime();
                    Date varDate = showTime.getDate();
                    String date = (varDate.getMonth()+1) + "/" + varDate.getDate() + "/" + (varDate.getYear()+1900);
                    if(date.equals(formatedDate) && showTime.getStartingTime() == particularStartingTime){
                        if(movieSet.contains(ticket.getShow().getMovieInfo().getTitle())){
                            continue;
                        }
                        movieSet.add(ticket.getShow().getMovieInfo().getTitle());

                        if(entry.isEmpty() == false){
                            entry = entry + ", ";
                        }
                        entry = entry + ticket.getShow().getMovieInfo().getTitle();
                    }
                }
                
                today.add(Calendar.DATE, 1);
                currentRow.add(entry);
            }
            for(int i = 1; i <= 7; i++){
                today.add(Calendar.DATE, -1);
            }
            scheduleTable.add(currentRow);
        }
        return scheduleTable;
    }

    public boolean hasAtLeastOneTicket(OneShow oneShow){
        List<Ticket> ticketList = ticketRepository.findAll();
        for(Ticket ticket : ticketList){
            if(ticket.getOneShow() == oneShow){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> generateSchedule(int id) {
        MovieInfo movieInfo = movieInfoService.findById(id);
        List<OneShow> oneShowList = oneShowRepository.findAll();
        List<String> scheduleTable = new ArrayList<>();

        for(OneShow oneShow : oneShowList){
            if(oneShow.getMovieInfo() == movieInfo && hasAtLeastOneTicket(oneShow)){
                String entry =  oneShow.getShowTime().getDateStringWithYear() + " " +
                                oneShow.getShowTime().getStartingTimeString() + " at theater " +
                                oneShow.getTheater().getId();

                scheduleTable.add(entry);
            }
        }
        Collections.sort(scheduleTable);
        return scheduleTable;
    }

    @Autowired
    TicketPriceRepository ticketPriceRepository;

    @Override
    public List<TicketPrice> allTicketPrice() {
            return ticketPriceRepository.findAll();
        }

    @Override
    public String getTicketPriceByType(String type) {
        return ticketPriceRepository.getPriceByType(type);
    }

    @Override
    public void savePrice(TypeOfTicket typeOfTicket, String price) {
        TicketPrice ticketPrice = new TicketPrice(typeOfTicket,price);
        //System.out.println("typeeeeeeeeeee "+ typeOfTicket);
        //System.out.println("priceeeeeeeee " + price);
        ticketPriceRepository.updatePrice(typeOfTicket.toString(),price);
    }

    @Override
    public Ticket getById(long id) {
        return ticketRepository.findByID(id);
    }

    @Override
    public String getBookingFee() {
        System.out.println("booking fee = " + ticketPriceRepository.getBookingFee());
        return ticketPriceRepository.getBookingFee();
    }

    @Override
    public void deleteFromCart(long id) {
        ticketRepository.deleteFromCart(id);
    }

    @Override
    public void updateBookingFeeForAll(String bookingFee) {
        ticketPriceRepository.updateBookingFee(bookingFee);
    }

}
