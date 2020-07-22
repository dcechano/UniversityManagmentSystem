package com.example.ums.repos.impl;

import com.example.ums.entities.Course;
import com.example.ums.entities.Department;
import com.example.ums.entities.person.impl.FacultyMember;
import com.example.ums.repos.FacultyMemberRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Transactional
public class FacultyMemberRepoImpl implements FacultyMemberRepo {

    private EntityManager entityManager;

    public FacultyMemberRepoImpl() {
    }

    @Override
    public void save(FacultyMember entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(FacultyMember entity) {
        if (entityManager.contains(entity)) {
            entityManager.remove(entity);
        } else {
            var newEntity = entityManager.merge(entity);
            entityManager.remove(newEntity);
        }
    }

    @Override
    public FacultyMember update(FacultyMember entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void deleteById(Long id) {
        FacultyMember facultyMember = entityManager.find(FacultyMember.class, id);
        delete(facultyMember);
    }

    @Override
    public Optional<FacultyMember> findById(Long id) {
        FacultyMember facultyMember = entityManager.find(FacultyMember.class, id);
        return Optional.ofNullable(facultyMember);
    }


    @Override
    public List<FacultyMember> findAll() {
        Query facultyQuery = entityManager.createQuery("SELECT f FROM FacultyMember f");

        return (List<FacultyMember>) facultyQuery.getResultList();
    }

    @Override
    public Map<Long, FacultyMember> findAllAsMap() {
        Map<Long, FacultyMember> facultyMap = new HashMap<>();
        for (FacultyMember member : findAll())
            facultyMap.put(member.getId(), member);

        return facultyMap;
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

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
