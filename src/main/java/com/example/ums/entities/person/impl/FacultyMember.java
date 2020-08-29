package com.example.ums.entities.person.impl;

import com.example.ums.entities.Course;
import com.example.ums.entities.Department;
import com.example.ums.entities.person.Person;
import com.example.ums.enums.EmploymentStatus;
import jdk.jfr.Name;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NamedEntityGraph(name = "faculty-courses",
    attributeNodes = {@NamedAttributeNode(value = "courses")})
@Entity
@Table(name = "FACULTYMEMBER")
public class FacultyMember extends Person {

    @OneToMany(mappedBy = "instructor")
    private List<Course> courses;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "DEPARTMENT")
    private Department department;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private EmploymentStatus status;

    @Column(name = "HIRING_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime hiringDate;

    public FacultyMember() {

    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public EmploymentStatus getStatus() {
        return status;
    }

    public void setStatus(EmploymentStatus status) {
        this.status = status;
    }

    public LocalDateTime getHiringDate() {
        return hiringDate;
    }
}
