package com.example.ums.controllers;

import com.example.ums.entities.person.impl.FacultyMember;
import com.example.ums.ex.EntityNotFoundException;
import com.example.ums.repos.CourseRepo;
import com.example.ums.repos.FacultyMemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@SessionAttributes(value = "faculty")
@Controller
@RequestMapping("/faculty_portal/{id}/")
public class FacultyPortalController {

    private FacultyMemberRepo facultyMemberRepo;
    private CourseRepo courseRepo;

    public FacultyPortalController(FacultyMemberRepo facultyMemberRepo) {
        this.facultyMemberRepo = facultyMemberRepo;
    }

    @GetMapping
    public String facultyPortal(@PathVariable("id") Long id, Model model) {
        Optional<FacultyMember> facultyOptional = facultyMemberRepo.getFacultyMemberWithCourses(id);
        facultyOptional.ifPresentOrElse(facultyMember -> model.addAttribute("faculty", facultyMember),
                () -> {
                    throw new EntityNotFoundException("Faculty member with id: " + id + " could not be found");
                });
        return "faculty_portal";
    }

    @GetMapping("/courses")
    public String facultyCourses(Model model, @ModelAttribute("faculty") FacultyMember member) {
        model.addAttribute("courses", member.getCourses());
        return "courses";
    }

    @Autowired
    public void setCourseRepo(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }
}
