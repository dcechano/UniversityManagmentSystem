package com.example.ums.entities;

import com.example.ums.entities.person.impl.FacultyMember;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "COURSE")
public class Course extends AbstractEntity{

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "INSTRUCTOR")
    private FacultyMember instructor;

    @OneToMany(mappedBy = "course")
    private Set<CourseGrade> studentGrades;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT")
    private Department department;

    public Course() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FacultyMember getInstructor() {
        return instructor;
    }

    public void setInstructor(FacultyMember instructor) {
        this.instructor = instructor;
    }

    public Set<CourseGrade> getStudentGrades() {
        return studentGrades;
    }

    public void setStudentGrades(Set<CourseGrade> studentGrades) {
        this.studentGrades = studentGrades;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Course{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
