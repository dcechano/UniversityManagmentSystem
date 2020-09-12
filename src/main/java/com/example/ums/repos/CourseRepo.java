package com.example.ums.repos;

import com.example.ums.entities.Course;
import com.example.ums.entities.CourseGrade;
import com.example.ums.entities.ScheduleDTO;

import java.util.List;

public interface CourseRepo extends AbstractRepo<Course>{

    Course findCourseByIdWithStudents(Long id);

    List<ScheduleDTO> findScheduleByStudentId(Long studentId);

    List<CourseGrade> getCourseGradesByCourseId(Long id);
}
