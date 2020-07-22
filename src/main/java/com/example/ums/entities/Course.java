package com.example.ums.entities;

import com.example.ums.entities.person.impl.FacultyMember;
import com.example.ums.entities.person.impl.Student;
import com.example.ums.enums.Grade;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
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

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", instructor=" + instructor +
                ", studentGrades=" + studentGrades +
                "} " + super.toString();
    }
}
