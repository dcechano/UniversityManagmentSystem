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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@Controller
@SessionAttributes(names = {"user", "student"})
@RequestMapping("/student_portal/{id}/")
public class StudentPortalController {

    private DepartmentRepo departmentRepo;

    private StudentRepo studentRepo;

    private CourseRepo courseRepo;


    @GetMapping
    public String studentPortal(@PathVariable("id") Long id, Model model) {
        Optional<Student> studentOptional = studentRepo.findStudentWithCourseGrades(id);
        studentOptional.ifPresentOrElse((student) -> model.addAttribute("student", student),
                () -> {
                    throw new EntityNotFoundException("Student with Id: " + id + " could not be found");
                });
        return "student_portal";
    }

    @GetMapping("/schedule")
    public String studentSchedule(Model model, @ModelAttribute("student") Student student){
        Set<CourseGrade> courseGrades = student.getCourseGrades();
        List<Course> courses = new ArrayList<>();
        for(CourseGrade courseGrade : courseGrades){
            courses.add(courseGrade.getCourse());
        }

        model.addAttribute("courses", courses);
        return "student_schedule";
    }

    @GetMapping("/manage")
    public String manage(Model model) {
        var departments = departmentRepo.findAll();

        model.addAttribute("departments", departments);
        return "manage";
    }

    @GetMapping("/show_courses")
    public String courses(@RequestParam("dep_id") Long depId, Model model) {

        Optional<Department> department = departmentRepo.getDepartmentWithCourses(depId);
        if (department.isEmpty()) {
            throw new EntityNotFoundException("Department with ID: " + depId + " could not be found");
        }
        model.addAttribute("department", department.get());

        return "show_courses";
    }

    @GetMapping("/add_course")
    public String addCourse(@RequestParam("course_id") Long courseId, @ModelAttribute("student") Student student) {

            Optional<Course> courseOptional = courseRepo.findById(courseId);
            courseOptional.ifPresentOrElse(course -> {
                        Set<CourseGrade> courseGrades = student.getCourseGrades();
                        CourseGrade courseGrade = new CourseGrade(student.getId(), courseId, course);
                        courseGrades.add(courseGrade);
                        studentRepo.update(student);
                    },
                    () -> {
                        throw new EntityNotFoundException("Course with Id: " + courseId + " could not be found");});

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

    @Autowired
    public void setDepartmentRepo(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

}
