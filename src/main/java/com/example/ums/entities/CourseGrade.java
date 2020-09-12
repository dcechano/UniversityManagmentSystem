package com.example.ums.entities;

import com.example.ums.entities.person.impl.Student;
import com.example.ums.enums.Grade;

import javax.persistence.*;

@Entity
@Table(name = "COURSE_GRADE")
public class CourseGrade{

    @EmbeddedId
    private CourseGradeKey id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId(value = "STUDENT_ID")
    @JoinColumn(name = "STUDENT_ID")
    private Student student;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId(value = "COURSE_ID")
    @JoinColumn(name = "COURSE_ID")
    private Course course;

    @Enumerated(EnumType.STRING)
    @Column(name = "GRADE")
    private Grade grade;

    public CourseGrade(Long studentId, Long courseId, Course course) {
        this.id = new CourseGradeKey(studentId, courseId);
        this.course = course;
    }

    public CourseGrade(CourseGradeKey courseGradeKey, Course course) {
        this.id = courseGradeKey;
        this.course = course;
    }

    public CourseGrade(){}

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
