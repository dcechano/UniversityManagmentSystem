package com.example.ums.entities.person.impl;

import com.example.ums.entities.Department;
import com.example.ums.entities.person.Person;
import com.example.ums.enums.EmploymentStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "STAFFMEMBER")
@PrimaryKeyJoinColumn(name = "ID")
public class StaffMember extends Person {

    @Column(name = "HIRING_DATE")
    private LocalDateTime hiringDate;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT")
    private Department department;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private EmploymentStatus status;

    public StaffMember() {
    }

    public LocalDateTime getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDateTime hiringDate) {
        this.hiringDate = hiringDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public EmploymentStatus getStatus() {
        return status;
    }

    public void setStatus(EmploymentStatus status) {
        this.status = status;
    }
}
