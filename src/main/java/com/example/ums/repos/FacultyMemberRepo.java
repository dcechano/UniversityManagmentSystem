package com.example.ums.repos;

import com.example.ums.entities.person.impl.FacultyMember;

import java.util.Optional;

public interface FacultyMemberRepo extends AbstractRepo<FacultyMember> {

    Optional<FacultyMember> getFacultyMemberWithCourses(Long facultyId);

    long count();

}
