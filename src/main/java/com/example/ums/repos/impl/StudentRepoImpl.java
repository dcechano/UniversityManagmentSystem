package com.example.ums.repos.impl;

import com.example.ums.entities.Program;
import com.example.ums.entities.person.impl.Student;
import com.example.ums.enums.AcademicStatus;
import com.example.ums.repos.StudentRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("unchecked")
@Repository
@Transactional
public class StudentRepoImpl extends AbstractRepoImpl<Student> implements StudentRepo {


    public StudentRepoImpl() {
        super(Student.class);
    }

    @Override
    public Program getMajorById(Long id) {
        Student student = entityManager.find(Student.class, id);
        return student.getMajor();
    }

    @Override
    public AcademicStatus getAcademicStatusById(Long id) {
        return entityManager.find(Student.class, id).getStatus();
    }

    @Override
    public Optional<Student> findStudentWithCourseGrades(Long id) {
        TypedQuery<Student> query = entityManager.createQuery(
                "SELECT s FROM Student  s JOIN FETCH s.courseGrades WHERE s.id =: id", Student.class);
        query.setParameter("id", id);
        Student student = query.getSingleResult();
        return Optional.ofNullable(student);
    }
}
