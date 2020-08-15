package com.example.ums.controllers;

import com.example.ums.entities.person.impl.FacultyMember;
import com.example.ums.ex.EntityNotFoundException;
import com.example.ums.repos.FacultyMemberRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequestMapping("/facultyPortal")
@Controller
public class FacultyPortalController {

    FacultyMemberRepo facultyMemberRepo;

    public FacultyPortalController(FacultyMemberRepo facultyMemberRepo) {
        this.facultyMemberRepo = facultyMemberRepo;
    }

// TODO Edit this method so that it can pull ANY faculty member from the database
    @RequestMapping
    public String facultyPortal(Model model) {
        Optional<FacultyMember> david = facultyMemberRepo.findById(1L);

        david.ifPresentOrElse(folk -> model.addAttribute("faculty", folk), () -> {
            throw new EntityNotFoundException("Could not find faculty member!");
        });
        return "faculty_portal";
    }
}
