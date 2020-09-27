package com.example.ums.controllers;

import com.example.ums.entities.Course;
import com.example.ums.entities.CourseGrade;
import com.example.ums.entities.Department;
import com.example.ums.entities.ScheduleDTO;
import com.example.ums.entities.person.impl.Student;
import com.example.ums.ex.EntityNotFoundException;
import com.example.ums.repos.CourseRepo;
import com.example.ums.repos.DepartmentRepo;
import com.example.ums.repos.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return "student_portal/student_portal";
    }

    @GetMapping("/schedule")
    public String studentSchedule(Model model, @PathVariable("id") Long id ,@ModelAttribute("student") Student student){
        List<ScheduleDTO> schedule = courseRepo.findScheduleByStudentId(student.getId());
//        List<CourseGrade> courses = courseRepo.getCourseGradesByCourseId(id);
        model.addAttribute("schedule", schedule);
        return "student_portal/student_schedule";
    }

    @GetMapping("/manage")
    public String manage(Model model) {
        var departments = departmentRepo.findAll();

        model.addAttribute("departments", departments);
        return "student_portal/manage";
    }

    @GetMapping("/show_courses")
    public String courses(@RequestParam("dep_id") Long depId, Model model) {

        Optional<Department> department = departmentRepo.getDepartmentWithCourses(depId);
        if (department.isEmpty()) {
            throw new EntityNotFoundException("Department with ID: " + depId + " could not be found");
        }
        model.addAttribute("department", department.get());

        return "student_portal/show_courses";
    }

    @GetMapping("/add_course")
    public String addCourse(@RequestParam("course_id") Long courseId, @ModelAttribute("student") Student student) {

            Optional<Course> courseOptional = courseRepo.findById(courseId);
            courseOptional.ifPresentOrElse(course -> {
                        List<CourseGrade> courseGrades = student.getCourseGrades();
                        CourseGrade courseGrade = new CourseGrade(student.getId(), courseId, course);
                        courseGrades.add(courseGrade);
                        studentRepo.merge(student);
                    },
                    () -> {
                        throw new EntityNotFoundException("Course with Id: " + courseId + " could not be found");});

        return "student_portal/manage";
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
