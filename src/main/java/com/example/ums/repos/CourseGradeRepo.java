package com.example.ums.repos;

import com.example.ums.entities.CourseGrade;

import java.util.List;
import java.util.Optional;

public interface CourseGradeRepo{

    void save(CourseGrade entity);

    void save(List<CourseGrade> entityList);

    CourseGrade merge(CourseGrade entity);

    List<CourseGrade> merge(List<CourseGrade> entityList);

    void delete(CourseGrade entity);

    void deleteById(Object id);

    Optional<CourseGrade> findById(Object id);

    List<CourseGrade> findAll();

}
