package com.example.ums.controllers;

import com.example.ums.entities.Department;
import com.example.ums.entities.Program;
import com.example.ums.repos.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private DepartmentRepo repo;

    @GetMapping("/manage")
    public String manage(Model model) {
        var departments = repo.findAll();

        model.addAttribute("departments", departments);
        return "manage";
    }

    @Autowired
    public void setRepo(DepartmentRepo repo) {
        this.repo = repo;
    }
}
