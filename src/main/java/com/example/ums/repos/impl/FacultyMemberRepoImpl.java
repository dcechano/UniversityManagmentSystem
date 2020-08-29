package com.example.ums.repos.impl;

import com.example.ums.entities.person.impl.FacultyMember;
import com.example.ums.repos.FacultyMemberRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@Repository
@Transactional
public class FacultyMemberRepoImpl extends AbstractRepoImpl<FacultyMember> implements FacultyMemberRepo {

    public FacultyMemberRepoImpl() {
        super(FacultyMember.class);
    }

    @Override
    public Optional<FacultyMember> getFacultyMemberWithCourses(Long facultyId) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("faculty-courses");
        FacultyMember facultyMember = entityManager.find(FacultyMember.class, facultyId, Map.of("javax.persistence.fetchgraph", entityGraph));
        return Optional.ofNullable(facultyMember);
    }

    @Override
    public long count() {
        return (long) entityManager.createQuery("SELECT COUNT(f) FROM FacultyMember f").getSingleResult();
    }
}
