package com.example.ums.entities;

import com.example.ums.entities.person.impl.FacultyMember;
import com.example.ums.entities.person.impl.StaffMember;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DEPARTMENT")
public class Department extends AbstractEntity {

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Program> programs;

    @OneToMany(mappedBy = "department")
    private List<FacultyMember> faculty;

    @OneToMany(mappedBy = "department")
    private List<StaffMember> staff;

    public Department() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public List<FacultyMember> getFaculty() {
        return faculty;
    }

    public void setFaculty(List<FacultyMember> faculty) {
        this.faculty = faculty;
    }

    public List<StaffMember> getStaff() {
        return staff;
    }

    public void setStaff(List<StaffMember> staff) {
        this.staff = staff;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", programs=" + programs +
                ", faculty=" + faculty +
                ", staff=" + staff +
                "} " + super.toString();
    }
}
