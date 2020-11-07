package com.example.ums.repos.impl;

import com.example.ums.entities.Course;
import com.example.ums.entities.CourseGrade;
import com.example.ums.dto.ScheduleDTO;
import com.example.ums.repos.CourseRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
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

        Query query = entityManager.createQuery("SELECT c FROM CourseGrade c FETCH ALL PROPERTIES WHERE c.id.courseId = :id");
        query.setParameter("id", id);

        return (List<CourseGrade>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ScheduleDTO> findScheduleByStudentId(Long studentId) {

        Query query = entityManager.createNativeQuery(
                "SELECT C.STUDENT_ID, C.COURSE_ID, C.GRADE, D.NAME, D.INSTRUCTOR FROM COURSE_GRADE C " +
                        "RIGHT JOIN COURSE D ON D.ID = COURSE_ID WHERE STUDENT_ID = :id");
        query.setParameter("id", studentId);

        List<Object[]> result = (List<Object[]>) query.getResultList();
        List<ScheduleDTO> list = new ArrayList<>();
        for (Object[] obj : result) {
            Query q = entityManager.createNativeQuery("SELECT P.LAST_NAME FROM PERSON P WHERE P.ID=:id");
            q.setParameter("id", obj[4]);
            String name = (String)  q.getSingleResult();
            list.add(new ScheduleDTO((String) obj[3], name, ((BigInteger) obj[1]).longValue()));
        }
        return list;
    }


}
