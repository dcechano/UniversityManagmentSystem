package com.example.ums.controllers;

import com.example.ums.entities.Department;
import com.example.ums.ex.EntityNotFoundException;
import com.example.ums.repos.DepartmentRepo;
import com.example.ums.repos.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

//    TODO make sure that the repos are completely necessary

    private DepartmentRepo repo;

    private PersonRepo personRepo;

    public RegistrationController(DepartmentRepo repo, PersonRepo personRepo) {
        this.repo = repo;
        this.personRepo = personRepo;
    }

    @GetMapping("/manage")
    public String manage(Model model) {
        var departments = repo.findAll();

        model.addAttribute("departments", departments);
        return "manage";
    }

    @GetMapping("/show_courses")
    public String courses(@RequestParam(name = "dep_id") Long depId, Model model) {

        Optional<Department> department = repo.getDepartmentWithCourses(depId);
        if (department.isEmpty()) {
            throw new EntityNotFoundException("Department with ID: " + depId + " could not be found");
        }
        model.addAttribute("department", department.get());

        return "show_courses";
    }

}
