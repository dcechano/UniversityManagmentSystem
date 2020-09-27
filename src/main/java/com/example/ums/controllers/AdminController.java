package com.example.ums.controllers;

import com.example.ums.entities.Role;
import com.example.ums.entities.person.Person;
import com.example.ums.entities.person.impl.Student;
import com.example.ums.repos.PersonRepo;
import com.example.ums.repos.RoleRepo;
import com.example.ums.repos.StudentRepo;
import com.example.ums.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RequestMapping("/admin/")
@Controller
public class AdminController {

    private Logger logger = Logger.getLogger(getClass().toString());

    private StudentRepo studentRepo;
    //    TODO potentially remove this repo if unnecessary
    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private PersonService personService;
    @Autowired
    private RoleRepo roleRepo;

    private PasswordEncoder passwordEncoder;

    @Autowired
    private DaoAuthenticationProvider provider;


    @RequestMapping
    public String admin() {
        return "admin_portal/admin_page";
    }

    @GetMapping("/register_student")
    public String registerStudent(Model model) {
        model.addAttribute("student", new Student());
        return "admin_portal/register_student";
    }

    @PostMapping("/save_student")
    public String saveStudent(@ModelAttribute("student") Student student) {
        logger.info("Attempting to save @ModelAttribute('student') with first name: "
                + student.getFirstName() + " and last name " + student.getLastName());
        personService.save(student);
        logger.info("Saved the new Student. Now going to retrieve student for debugging");
        Person person = personRepo.findByUsername("Smiller");
        logger.info("Person was successfully retrieved. Printing the roles");
        logger.info(((Role) person.getRoles().toArray()[0]).getRole().name());
        logger.info("Roles printed. Now going to get Userdetails for debugging");
        UserDetails user = personService.loadUserByUsername("Smiller");
        logger.info("UserDetails retrieved. Displaying details");
        logger.info("Username: " + user.getUsername());
        logger.info("Password: " + user.getPassword());
        logger.info("password = password? - " + passwordEncoder.matches("password", user.getPassword()));
        logger.info("Authorities: " + user.getAuthorities());
        logger.info("Finished saving student. Redirecting to /login");
        return "redirect:/login";
    }

    @GetMapping("/list_students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentRepo.findAll());
        model.addAttribute("people", personRepo.findAll());
        return "admin_portal/all_students";
    }

    @RequestMapping("/remove_student")
    public String removeStudent(@RequestParam("student_id") Long studentId) {
        personRepo.deleteById(studentId);
        return "redirect:list_students";
    }

    @Autowired
    public void setStudentRepo(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
