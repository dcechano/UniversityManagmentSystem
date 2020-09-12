package com.example.ums.repos.impl;

import com.example.ums.entities.Course;
import com.example.ums.entities.CourseGrade;
import com.example.ums.entities.ScheduleDTO;
import com.example.ums.entities.person.impl.Student;
import com.example.ums.repos.CourseRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


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

//    TODO clean up this method
    @SuppressWarnings("unchecked")
    @Override
    public List<ScheduleDTO> findScheduleByStudentId(Long studentId) {
        Logger logger = Logger.getLogger(getClass().toString());

        Query query = entityManager.createNativeQuery(
                "SELECT C.STUDENT_ID, C.COURSE_ID, D.NAME, D.INSTRUCTOR FROM COURSE_GRADE C RIGHT JOIN COURSE D ON D.ID = COURSE_ID WHERE STUDENT_ID = :id");
        query.setParameter("id", studentId);

        List<Object[]> result = (List<Object[]>) query.getResultList();
        logger.info("The size of the resultList is: " + result.size());
        List<ScheduleDTO> list = new ArrayList<>();
        for (Object[] obj : result) {
            logger.info("The number of columns is: " + obj.length);
            logger.info("printing query columns");
            for (Object object : obj) {
                logger.info(object.toString());
            }
            Query q = entityManager.createNativeQuery("SELECT F.LAST_NAME FROM FACULTYMEMBER F WHERE F.ID=:id");
            q.setParameter("id", obj[3]);
            String name = (String)  q.getSingleResult();
            logger.info("Instructor name is : " + name);

            list.add(new ScheduleDTO((String) obj[2], name));
        }
        return list;
    }
}