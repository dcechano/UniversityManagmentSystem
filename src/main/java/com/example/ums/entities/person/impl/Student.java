package com.example.ums.entities.person.impl;

import com.example.ums.entities.CourseGrade;
import com.example.ums.entities.Program;
import com.example.ums.entities.person.Person;
import com.example.ums.enums.AcademicStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NamedEntityGraph(
        name = "course-grades",
        attributeNodes = {
                @NamedAttributeNode(value = "courseGrades", subgraph = "student-course")
        },
        subgraphs = {
                @NamedSubgraph(name = "student-course",
                attributeNodes = {
                        @NamedAttributeNode("student"),
                        @NamedAttributeNode("course")
                })
        }
)
@Entity
@Table(name = "STUDENT")
@PrimaryKeyJoinColumn(name = "ID")
public class Student extends Person {

    @Column(name = "ENROLLMENT_DATE")
    private LocalDateTime enrollmentDate;

    @ManyToOne
    @JoinColumn(name = "MAJOR")
    private Program major;

    @OneToMany(mappedBy = "student")
    private List<CourseGrade> courseGrades;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private AcademicStatus status;

    public Student() {
    }

    @PreUpdate
    @PrePersist
    private void update() {
        modifiedAt = LocalDateTime.now();
    }

    public List<CourseGrade> getCourseGrades() {
        return courseGrades;
    }

    public void setCourseGrades(List<CourseGrade> courseGrades) {
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
