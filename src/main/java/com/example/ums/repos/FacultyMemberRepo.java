package com.example.ums.repos;

import com.example.ums.entities.Course;
import com.example.ums.entities.CourseGrade;
import com.example.ums.entities.Department;
import com.example.ums.entities.person.impl.FacultyMember;

import java.util.List;
import java.util.Optional;

public interface FacultyMemberRepo extends AbstractRepo<FacultyMember> {

    Optional<FacultyMember> getFacultyMemberWithCourses(Long facultyId);

    long count();

}
