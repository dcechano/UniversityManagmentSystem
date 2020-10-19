package com.example.ums.controllers;

import com.example.ums.dto.FacultyDTO;
import com.example.ums.entities.Department;
import com.example.ums.entities.person.impl.FacultyMember;
import com.example.ums.entities.person.impl.Student;
import com.example.ums.enums.RoleEnum;
import com.example.ums.ex.EntityNotFoundException;
import com.example.ums.repos.*;
import com.example.ums.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RequestMapping("/admin/")
@Controller
public class AdminController {

    private final Logger logger = Logger.getLogger(getClass().toString());

    private StudentRepo studentRepo;
    //    TODO potentially remove this repo if unnecessary
    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private FacultyMemberRepo facultyMemberRepo;
    @Autowired
    private PersonService personService;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private DepartmentRepo departmentRepo;


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
        student.setRoles(List.of(roleRepo.getRoleByName(RoleEnum.ROLE_STUDENT.name())));
        personService.save(student);
        return "redirect:/admin/";
    }

    @GetMapping("/modify_student/{id}")
    public String modifyStudent(Model model, @PathVariable("id") Long id) {
        Optional<Student> optional = studentRepo.findById(id);
        optional.ifPresentOrElse((student) ->
                {
                    model.addAttribute("student", student);
                },
                () -> {
                    throw new EntityNotFoundException("Student with id: " + id + " could not be found");
                }
        );
        return "admin_portal/update_student";
    }

    @PostMapping("/update_student")
    public String updateStudent(@ModelAttribute("student") Student student) {
        student.setRoles(List.of(roleRepo.getRoleByName(RoleEnum.ROLE_STUDENT.name())));
        studentRepo.merge(student);
        return "redirect:/admin/";
    }

    @GetMapping("/register_faculty")
    public String registerFaculty(Model model) {
        List<Department> departments = departmentRepo.findAll();
        FacultyDTO dto = new FacultyDTO();
        dto.setDepartments(departments);
        model.addAttribute("faculty", dto);
        return "admin_portal/register_faculty";
    }

    @PostMapping("/save_faculty")
    public String saveFaculty(@ModelAttribute("faculty") FacultyDTO facultyDTO) {
        FacultyMember facultyMember = facultyDTO.getFacultyMember();
        facultyMember.setRoles(List.of(roleRepo.getRoleByName(RoleEnum.ROLE_FACULTY_MEMBER.name())));
        //        personService.save(facultyMember);
        Logger logger = Logger.getLogger(getClass().toString());
        logger.info("First name: " + facultyMember.getFirstName());
        logger.info("Last name: " + facultyMember.getLastName());
        facultyMemberRepo.save(facultyMember);

        return "redirect:/admin/";
    }

    @RequestMapping("/remove_faculty")
    public String removeFaculty(@RequestParam("id") Long facultyId) {
        personRepo.deleteById(facultyId);
        return "redirect:list_students";
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

    @GetMapping("/modify_faculty/{id}")
    public String modifyFaculty(Model model, @PathVariable("id") Long id) {
        Optional<FacultyMember> optional = facultyMemberRepo.findById(id);
        List<Department> departments = departmentRepo.findAll();
        optional.ifPresentOrElse((faculty) ->
                {
                    FacultyDTO dto = new FacultyDTO(faculty);
                    dto.setDepartments(departments);
                    model.addAttribute("faculty", dto);
                    model.addAttribute("departments", departments);

                },
                () -> {
                    throw new EntityNotFoundException("Faculty Member with id: " + id + " could not be found");
                }
        );
        return "admin_portal/update_faculty";
    }

    @PostMapping("/update_faculty")
    public String updateFaculty(@ModelAttribute("faculty") FacultyDTO facultyDTO) {
        FacultyMember facultyMember = facultyDTO.getFacultyMember();
        facultyMember.setRoles(List.of(roleRepo.getRoleByName(RoleEnum.ROLE_FACULTY_MEMBER.name())));
        facultyMemberRepo.merge(facultyDTO.getFacultyMember());
        return "redirect:/admin/";
    }

    @Autowired
    public void setStudentRepo(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

}
