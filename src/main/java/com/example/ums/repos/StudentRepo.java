package com.example.ums.repos;

import com.example.ums.entities.CourseGrade;
import com.example.ums.entities.Program;
import com.example.ums.entities.person.impl.Student;
import com.example.ums.enums.AcademicStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface StudentRepo extends AbstractRepo<Student> {

    List<Student> findAll();

    Map<Long, Student> findAllAsMap();

    Program getMajorById(Long id);

    AcademicStatus getAcademicStatusById(Long id);

    Set<CourseGrade> getCourseGradesById(Long id);
}
