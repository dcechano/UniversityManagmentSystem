package com.example.ums.repos;

import com.example.ums.entities.Course;
import com.example.ums.entities.Department;
import com.example.ums.entities.person.impl.FacultyMember;

import java.util.List;

public interface FacultyMemberRepo extends AbstractRepo<FacultyMember> {

    List<Course> getCoursesByFacultyId(Long facultyId);

    long count();

}
