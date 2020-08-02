package com.example.ums.controllers;

import com.example.ums.repos.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class BaseController {

    Logger logger;

    @Autowired
    DepartmentRepo repo;

    public BaseController() {
        this.logger = Logger.getLogger(getClass().toString());
    }

    @RequestMapping
    public String home(Model model) {
        return "landing";
    }

    @RequestMapping("/studentPortal")
    public String studentPortal() {
        return "portal";
    }
}
