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

    Logger logger;

//    TODO remove any of these repos if they are unnecessary
    @Autowired
    PersonRepo personRepo;

    @Autowired
    DepartmentRepo departmentRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    StudentRepo studentRepo;

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
//  TODO Remove? or at least move to a place more appropriate!
    @RequestMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

    // TODO Decide if theses portals merit their own controllers or if they should be deleted
    @RequestMapping("/studentPortal")
    public String studentPortal(Model model, @SessionAttribute("user") Person person) {
        Optional<Student> studentOptional = studentRepo.findById(person.getId());
        studentOptional.ifPresentOrElse(student -> {
                    model.addAttribute("student", student);
                },
                () -> {
                    throw new EntityNotFoundException("No Student found with Id: " + person.getId());
                });

        return "student_portal";
    }

//    TODO Fix this method: Either add as StudentPortal Controller or change mapping from /student/schedule
    @RequestMapping("/student/schedule")
    public String studentSchedule(@RequestParam("student_id") Long studentId, Model model){
        Optional<Student> studentOptional = studentRepo.findStudentWithCourseGrades(studentId);
        studentOptional.ifPresentOrElse(student -> {
                    Set<CourseGrade> courseGrades = student.getCourseGrades();
                    List<Course> courses = new ArrayList<>();
                    for(CourseGrade courseGrade : courseGrades){
                        courses.add(courseGrade.getCourse());
                    }
                    model.addAttribute("courses", courses);
                },
                () -> {
                    throw new EntityNotFoundException("No Student found with Id: " + studentId);
                });
        return "student_schedule";
    }

}
