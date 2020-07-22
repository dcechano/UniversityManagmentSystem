package com.example.ums.entities;

import javax.persistence.*;

@Entity
@Table(name = "PROGRAM")
public class Program extends AbstractEntity{

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT")
    private Department department;

    public Program() {
    }

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Program{" +
                "name='" + name + '\'' +
                ", department=" + department +
                "} " + super.toString();
    }
}

