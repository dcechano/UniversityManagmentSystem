package com.example.ums.entities.person.impl;

import com.example.ums.entities.CourseGrade;
import com.example.ums.entities.Program;
import com.example.ums.entities.person.Person;
import com.example.ums.enums.AcademicStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@NamedEntityGraph(
        name = "course-grades",
        attributeNodes = {
                @NamedAttributeNode(value = "courseGrades")
        }
)
@Entity
@Table(name = "STUDENT")
public class Student extends Person {

    @Column(name = "ENROLLMENT_DATE")
    private LocalDateTime enrollmentDate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "MAJOR")
    private Program major;

    @OneToMany(mappedBy = "student")
    private Set<CourseGrade> courseGrades;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private AcademicStatus status;

    public Set<CourseGrade> getCourseGrades() {
        return courseGrades;
    }

    public void setCourseGrades(Set<CourseGrade> courseGrades) {
        this.courseGrades = courseGrades;
    }

    public Program getMajor() {
        return major;
    }

    public void setMajor(Program major) {
        this.major = major;
    }

    public AcademicStatus getStatus() {
        return status;
    }

    public void setStatus(AcademicStatus status) {
        this.status = status;
    }

    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDateTime enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
