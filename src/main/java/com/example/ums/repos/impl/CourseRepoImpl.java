package com.example.ums.repos.impl;

import com.example.ums.entities.Course;
import com.example.ums.entities.CourseGrade;
import com.example.ums.repos.CourseRepo;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.persistence.EntityGraph;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;


@Repository
@Transactional
public class CourseRepoImpl extends AbstractRepoImpl<Course> implements CourseRepo {


    public CourseRepoImpl() {
        super(Course.class);
    }

    @Override
    public Course findCourseByIdWithStudents(Long id) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("course-students");
        return entityManager.find(Course.class, id, Map.of("javax.persistence.fetchgraph", entityGraph));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CourseGrade> getCourseGradesByCourseId(Long id) {
        TypedQuery<CourseGrade> query = entityManager.createQuery("SELECT c FROM CourseGrade c " +
                " INNER JOIN Student s ON s.id = c.student.id WHERE c.id.courseId = :id", CourseGrade.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
