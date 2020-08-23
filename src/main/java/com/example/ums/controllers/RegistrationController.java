package com.example.ums.controllers;

import com.example.ums.entities.Course;
import com.example.ums.entities.CourseGrade;
import com.example.ums.entities.Department;
import com.example.ums.entities.person.Person;
import com.example.ums.entities.person.impl.Student;
import com.example.ums.ex.EntityNotFoundException;
import com.example.ums.repos.CourseRepo;
import com.example.ums.repos.DepartmentRepo;
import com.example.ums.repos.PersonRepo;
import com.example.ums.repos.StudentRepo;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

//    TODO make sure that the repos are completely necessary

    private DepartmentRepo repo;

    private PersonRepo personRepo;

    private StudentRepo studentRepo;

    private CourseRepo courseRepo;

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

    @GetMapping("/add_course")
    public String addCourse(@RequestParam("course_id") Long courseId, @SessionAttribute("user") Person person) {

        Optional<Student> studentOptional = studentRepo.findStudentWithCourseGrades(person.getId());
        studentOptional.ifPresentOrElse(student -> {

            Optional<Course> courseOptional = courseRepo.findById(courseId);
            courseOptional.ifPresentOrElse(course -> {
                        Set<CourseGrade> courseGrades = student.getCourseGrades();
                        CourseGrade courseGrade = new CourseGrade(student.getId(), courseId, course);
                        if (courseGrades == null) {
                            courseGrades = Set.of(courseGrade);
                        } else courseGrades.add(courseGrade);
                        studentRepo.update(student);
                    },
                    () -> {
                        throw new EntityNotFoundException("Course with Id: " + courseId + " could not be found");});
        }, () -> {
            throw new EntityNotFoundException("Student with id: " + person.getId() + "Could not be found!");
        });

        return "redirect:manage";
    }


    @Autowired
    public void setCourseRepo(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @Autowired
    public void setStudentRepo(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }
}
