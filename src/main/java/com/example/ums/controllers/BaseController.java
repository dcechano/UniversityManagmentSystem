package com.example.ums.controllers;

import com.example.ums.entities.Role;
import com.example.ums.entities.person.Person;
import com.example.ums.enums.RoleEnum;
import com.example.ums.repos.DepartmentRepo;
import com.example.ums.repos.PersonRepo;
import com.example.ums.repos.impl.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class BaseController {

    Logger logger;

//    TODO remove any of these repos if they are unnecessary
    @Autowired
    PersonRepo personRepo;

    @Autowired
    DepartmentRepo departmentRepo;

    @Autowired
    RoleRepo roleRepo;

    public BaseController(PersonRepo personRepo, DepartmentRepo departmentRepo, RoleRepo roleRepo) {
        this.personRepo = personRepo;
        this.departmentRepo = departmentRepo;
        this.roleRepo = roleRepo;
    }

    public BaseController() {
        this.logger = Logger.getLogger(getClass().toString());
    }

    @RequestMapping
    public String home(Model model) {
        return "landing";
    }

    @RequestMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
// TODO Decide if theses portals merit their own controllers or if they should be deleted
    @RequestMapping("/studentPortal")
    public String studentPortal() {
        return "student_portal";
    }

//    @GetMapping("/facultyPortal")
//    public String facultyPortal() {
//        return "faculty_portal";
//    }
}
