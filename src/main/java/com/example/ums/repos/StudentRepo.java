package com.example.ums.repos;

import com.example.ums.entities.Program;
import com.example.ums.entities.person.impl.Student;
import com.example.ums.enums.AcademicStatus;

import java.util.Optional;

public interface StudentRepo extends AbstractRepo<Student> {

    Program getMajorById(Long id);

    AcademicStatus getAcademicStatusById(Long id);

    Optional<Student> findStudentWithCourseGrades(Long id);

}
