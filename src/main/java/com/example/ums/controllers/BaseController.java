package com.example.ums.controllers;

import com.example.ums.dto.TourDTO;
import com.example.ums.entities.Tour;
import com.example.ums.repos.TourRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SessionAttributes("user")
@Controller
@RequestMapping("/")
public class BaseController {

    private TourRepo tourRepo;

    @RequestMapping
    public String home() {
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
        return "redirect:/admin/";
    }

    @Autowired
    public void setTourRepo(TourRepo tourRepo) {
        this.tourRepo = tourRepo;
    }
}
