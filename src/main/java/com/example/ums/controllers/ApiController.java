package com.example.ums.controllers;

import com.example.ums.entities.person.impl.Student;
import com.example.ums.repos.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private StudentRepo repo;

    @GetMapping("/all")
    public List<String> all() {
        List<String> names = new ArrayList<>();
        repo.findAll().forEach(s -> names.add(s.getFirstName()));
        return names;
    }

    @Autowired
    public void setRepo(StudentRepo repo) {
        this.repo = repo;
    }
}
