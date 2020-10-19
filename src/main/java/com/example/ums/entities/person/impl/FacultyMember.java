package com.example.ums.entities.person.impl;

import com.example.ums.entities.Course;
import com.example.ums.entities.Department;
import com.example.ums.entities.person.Person;
import com.example.ums.enums.EmploymentStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NamedEntityGraph(name = "faculty-courses",
    attributeNodes = {@NamedAttributeNode(value = "courses")})
@Entity
@Table(name = "FACULTYMEMBER")
@PrimaryKeyJoinColumn(name = "ID")
public class FacultyMember extends Person {

    @OneToMany(mappedBy = "instructor")
    private List<Course> courses;

//    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @ManyToOne
    @JoinColumn(name = "DEPARTMENT")
    private Department department;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private EmploymentStatus status;

    @Column(name = "HIRING_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate hiringDate;

    public FacultyMember() {

    }

    @PrePersist
    @PreUpdate
    public void update() {
        modifiedAt = LocalDateTime.now();
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

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    public EmploymentStatus getStatus() {
        return status;
    }

    public void setStatus(EmploymentStatus status) {
        this.status = status;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FacultyMember{");
        sb.append("username='").append(username).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
