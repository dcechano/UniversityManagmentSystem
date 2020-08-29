package com.example.ums.controllers;

import com.example.ums.entities.Course;
import com.example.ums.entities.CourseGrade;
import com.example.ums.entities.person.Person;
import com.example.ums.entities.person.impl.Student;
import com.example.ums.ex.EntityNotFoundException;
import com.example.ums.repos.DepartmentRepo;
import com.example.ums.repos.PersonRepo;
import com.example.ums.repos.StudentRepo;
import com.example.ums.repos.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class BaseController {

    @RequestMapping
    public String home(Model model, HttpSession session) {
        Person person = (Person) session.getAttribute("user");
        model.addAttribute("user", person);
        return "landing";
    }

//  TODO Remove? or at least move to a place more appropriate!
    @RequestMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }



}
