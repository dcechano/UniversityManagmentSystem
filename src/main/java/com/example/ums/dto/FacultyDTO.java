package com.example.ums.dto;

import com.example.ums.entities.Department;
import com.example.ums.entities.person.impl.FacultyMember;
import com.example.ums.enums.EmploymentStatus;
import com.example.ums.enums.RoleEnum;
import com.example.ums.repos.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FacultyDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String depName;
    private String hiringDate;
    private List<Department> departments;


    public FacultyDTO() {
    }

    public FacultyDTO(FacultyMember facultyMember) {
        id = facultyMember.getId();
        firstName = facultyMember.getFirstName();
        lastName = facultyMember.getLastName();
        password = facultyMember.getPassword();
        depName = facultyMember.getDepartment().getName();
        hiringDate = facultyMember.getHiringDate().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(String hiringDate) {
        this.hiringDate = hiringDate;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public FacultyMember getFacultyMember() {
        Department department = null;
        for (Department dep : departments) {
            if (this.depName.equals(dep.getName())) {
                department = dep;
            }
        }
        if (department == null) {
            throw new RuntimeException("Department could not be assigned for name: " + depName);
        }
        FacultyMember facultyMember = new FacultyMember();
        facultyMember.setId(id);
        facultyMember.setFirstName(firstName);
        facultyMember.setLastName(lastName);
        facultyMember.setUsername(firstName.charAt(0) + lastName);
        facultyMember.setPassword(password);
        facultyMember.setStatus(EmploymentStatus.FULL_TIME);
        facultyMember.setHiringDate(LocalDate.parse(hiringDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        facultyMember.setDepartment(department);
        return facultyMember;
    }
}
