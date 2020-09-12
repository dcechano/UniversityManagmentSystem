package com.example.ums.entities;

import com.example.ums.entities.person.impl.Student;

public class ScheduleDTO {
    private String course;
    private String instructor;

    public ScheduleDTO() {
    }

    public ScheduleDTO(String course, String instructor) {
        this.course = course;
        this.instructor = instructor;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
}