package com.example.ums.controllers;

import com.example.ums.entities.Course;
import com.example.ums.entities.CourseGrade;
import com.example.ums.entities.CourseGradeDTO;
import com.example.ums.entities.person.impl.FacultyMember;
import com.example.ums.entities.person.impl.Student;
import com.example.ums.enums.Grade;
import com.example.ums.ex.EntityNotFoundException;
import com.example.ums.repos.CourseGradeRepo;
import com.example.ums.repos.CourseRepo;
import com.example.ums.repos.FacultyMemberRepo;
import org.apache.commons.io.output.CloseShieldOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@SessionAttributes(value = "faculty")
@Controller
@RequestMapping("/faculty_portal/{id}/")
public class FacultyPortalController {

    private FacultyMemberRepo facultyMemberRepo;
    private CourseRepo courseRepo;
    private CourseGradeRepo courseGradeRepo;

    public FacultyPortalController(FacultyMemberRepo facultyMemberRepo) {
        this.facultyMemberRepo = facultyMemberRepo;
    }

    @GetMapping
    public String facultyPortal(@PathVariable("id") Long id, Model model) {
        Optional<FacultyMember> facultyOptional = facultyMemberRepo.getFacultyMemberWithCourses(id);
        facultyOptional.ifPresentOrElse(facultyMember -> model.addAttribute("faculty", facultyMember),
                () -> {
                    throw new EntityNotFoundException("Faculty member with id: " + id + " could not be found");
                });
        return "faculty_portal";
    }

    @GetMapping("/courses")
    public String facultyCourses(Model model, @ModelAttribute("faculty") FacultyMember member) {
        List<Course> courses = new ArrayList<>();
        for (Course course : member.getCourses()) {
            courses.add(courseRepo.findCourseByIdWithStudents(course.getId()));
        }
        model.addAttribute("courses", courses);
        return "courses";
    }

    @GetMapping("/grades")
    public String gradeStudents(Model model, @RequestParam("course_id") Long courseId) {
        List<CourseGrade> courseGrades = courseRepo.getCourseGradesByCourseId(courseId);
        if (courseGrades == null || courseGrades.size() == 0) {
            throw new RuntimeException("course grades didnt fetch");
        }
        courseGrades = courseGrades.stream()
                .sorted(Comparator.comparing(o -> o.getStudent().getLastName()))
                .collect(Collectors.toList());



        model.addAttribute("courseGrades", courseGrades);
        model.addAttribute("form", new CourseGradeDTO(courseGrades));
        model.addAttribute("newStudent", new Student());
        return "grades";
    }

    @PostMapping("/process_data")
    public String processData(@ModelAttribute("form") CourseGradeDTO courseGradeDTO) {
        List<CourseGrade> courseGrades = courseGradeDTO.getCourseGrades();
        courseGradeRepo.merge(courseGrades);

        return "redirect:courses";
    }

    @Autowired
    public void setCourseRepo(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @Autowired
    public void setCourseGradeRepo(CourseGradeRepo courseGradeRepo) {
        this.courseGradeRepo = courseGradeRepo;
    }
}
