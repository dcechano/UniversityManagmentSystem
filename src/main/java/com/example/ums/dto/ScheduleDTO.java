package com.example.ums.dto;

public class ScheduleDTO {
    private String course;
    private String instructor;
    private Long courseId;

    public ScheduleDTO() {
    }

    public ScheduleDTO(String course, String instructor) {
        this.course = course;
        this.instructor = instructor;
    }

    public ScheduleDTO(String course, String instructor, Long courseId) {
        this.course = course;
        this.instructor = instructor;
        this.courseId = courseId;
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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}