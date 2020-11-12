package com.example.ums.entities;

import com.example.ums.entities.person.impl.Student;
import com.example.ums.enums.Grade;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "COURSE_GRADE")
public class CourseGrade{

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

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "MODIFIED_AT")
    private LocalDateTime modifiedAt;

    public CourseGrade(Long studentId, Long courseId, Course course) {
        this.id = new CourseGradeKey(studentId, courseId);
        this.course = course;
    }

    public CourseGrade(CourseGradeKey courseGradeKey, Course course) {
        this.id = courseGradeKey;
        this.course = course;
    }

    public CourseGrade(Long studentId, Long courseId) {
        this.id = new CourseGradeKey(studentId, courseId);
    }

    public CourseGrade(){
    }

    @PreUpdate
    @PrePersist
    public void update() {
        modifiedAt = LocalDateTime.now();
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

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
