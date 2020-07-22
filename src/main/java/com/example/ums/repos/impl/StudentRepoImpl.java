package com.example.ums.repos.impl;

import com.example.ums.entities.CourseGrade;
import com.example.ums.entities.Program;
import com.example.ums.entities.person.impl.Student;
import com.example.ums.enums.AcademicStatus;
import com.example.ums.enums.converter.AcademicStatusConverter;
import com.example.ums.repos.StudentRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.*;

@Repository
@Transactional
public class StudentRepoImpl implements StudentRepo {

    private EntityManager entityManager;

    @Override
    public List<Student> findAll() {
        return (List<Student>) entityManager.createQuery("SELECT s FROM Student s").getResultList();
    }

    @Override
    public Map<Long, Student> findAllAsMap() {
        Map<Long, Student> map = new HashMap<>();
        for (Student student : findAll())
            map.put(student.getId(), student);

        return map;
    }

    @Override
    public Program getMajorById(Long id) {
//        Query query = entityManager.createQuery(
//                "SELECT s FROM Student s WHERE s.id=" + id
//        );
//        Student student = (Student) query.getSingleResult();
        //        return ((Student) query.getSingleResult()).getMajor();
        Student student = entityManager.find(Student.class, id);
        return student.getMajor();
    }

    @Override
    public AcademicStatus getAcademicStatusById(Long id) {
        return entityManager.find(Student.class, id).getStatus();
    }

    @Override
    public Set<CourseGrade> getCourseGradesById(Long id) {
        Query query = entityManager.createQuery(
                "SELECT s FROM Student s INNER JOIN s.courseGrades");
        return ((Student) query.getSingleResult()).getCourseGrades();
    }

    @Override
    public void save(Student entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Student entity) {
        if (entityManager.contains(entity)) {
            entityManager.remove(entity);
        } else {
            var newEntity = entityManager.merge(entity);
            entityManager.remove(newEntity);
        }
    }

    @Override
    public Student update(Student entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void deleteById(Long id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }

    @Override
    public Optional<Student> findById(Long id) {
        Student student = entityManager.find(Student.class, id);
        return Optional.ofNullable(student);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
