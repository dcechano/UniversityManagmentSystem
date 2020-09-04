package com.example.ums.entities;

import com.example.ums.entities.person.impl.FacultyMember;
import com.example.ums.entities.person.impl.Student;

import javax.persistence.*;
import java.util.List;

@NamedEntityGraph(name = "course-students",
    attributeNodes = @NamedAttributeNode("students"))
@Entity
@Table(name = "COURSE")
public class Course extends AbstractEntity{

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "INSTRUCTOR")
    private FacultyMember instructor;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT")
    private Department department;

    @ManyToMany
    @JoinTable(name = "COURSE_GRADE",
        joinColumns = @JoinColumn(name = "COURSE_ID"),
        inverseJoinColumns = @JoinColumn(name = "STUDENT_ID"))
    private List<Student> students;

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Course{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
