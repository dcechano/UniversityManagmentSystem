package com.example.ums.controllers;

import com.example.ums.dto.TourDTO;
import com.example.ums.repos.TourRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@SessionAttributes("user")
@Controller
@RequestMapping("/")
public class BaseController {

    private TourRepo tourRepo;

    @RequestMapping
    public String home(Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLL d, yyyy");
        String date1 = formatter.format(LocalDate.now());
        String date2 = formatter.format(LocalDate.now().plusDays(1));
        String date3 = formatter.format(LocalDate.now().plusDays(5));
        model.addAllAttributes(Map.of("date1", date1,
                "date2", date2,
                "date3", date3));
        return "landing";
    }

    @GetMapping("/schedule_tour")
    public String scheduleTour(Model model) {
        model.addAttribute("tourDTO", new TourDTO());
        return "schedule_tour";
    }

    @PostMapping("/schedule_tour")
    public String postTour(@ModelAttribute("tour") TourDTO tourDTO) {
        tourRepo.save(tourDTO.getTour());
        return "redirect:/";
    }

    @Autowired
    public void setTourRepo(TourRepo tourRepo) {
        this.tourRepo = tourRepo;
    }
}
