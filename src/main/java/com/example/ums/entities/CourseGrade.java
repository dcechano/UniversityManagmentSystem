package com.example.ums.entities;

import com.example.ums.entities.person.impl.Student;
import com.example.ums.enums.Grade;

import javax.persistence.*;

@Entity
@Table(name = "COURSE_GRADE")
public class CourseGrade {

    @EmbeddedId
    private CourseGradeKey id;

    @ManyToOne
    @MapsId(value = "STUDENT_ID")
    @JoinColumn(name = "STUDENT_ID")
    private Student student;

    @ManyToOne
    @MapsId(value = "COURSE_ID")
    @JoinColumn(name = "COURSE_ID")
    private Course course;

    @Enumerated(EnumType.STRING)
    @Column(name = "GRADE")
    private Grade grade;

    public CourseGrade() {
    }


    public CourseGradeKey getId() {
        return id;
    }

    public void setId(CourseGradeKey id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
