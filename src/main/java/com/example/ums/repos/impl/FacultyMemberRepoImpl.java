package com.example.ums.repos.impl;

import com.example.ums.entities.Course;
import com.example.ums.entities.person.impl.FacultyMember;
import com.example.ums.repos.FacultyMemberRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class FacultyMemberRepoImpl extends AbstractRepoImpl<FacultyMember> implements FacultyMemberRepo {

    public FacultyMemberRepoImpl() {
        super(FacultyMember.class);
    }

    @Override
    public List<Course> getCoursesByFacultyId(Long facultyId) {
        Query query = entityManager.createQuery(
                "SELECT c FROM Course c WHERE c.instructor=" + facultyId
        );
        return (List<Course>) query.getResultList();
    }

    @Override
    public long count() {
        return (long) entityManager.createQuery("SELECT COUNT(f) FROM FacultyMember f").getSingleResult();
    }
}
