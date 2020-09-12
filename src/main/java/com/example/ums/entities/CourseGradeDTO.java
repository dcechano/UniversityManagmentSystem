package com.example.ums.entities;

import java.util.ArrayList;
import java.util.List;

/**
* This class is simply used to wrap data about course grades into an object
* so that it can be easily dealt with by Thymeleaf.
*/
public class CourseGradeDTO{
    List<CourseGrade> courseGrades = new ArrayList<>();

    public CourseGradeDTO() {
    }

    public CourseGradeDTO(List<CourseGrade> courseGrades) {
        this.courseGrades = courseGrades;
    }

    public void addCourseGrade(CourseGrade courseGrade) {
        courseGrades.add(courseGrade);
    }


    public List<CourseGrade> getCourseGrades() {
        return courseGrades;
    }

    public void setCourseGrades(List<CourseGrade> courseGrades) {
        this.courseGrades = courseGrades;
    }
}