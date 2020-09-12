package com.example.ums.repos.impl;

import com.example.ums.entities.CourseGrade;
import com.example.ums.repos.CourseGradeRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class CourseGradeImpl implements CourseGradeRepo {

    private EntityManager entityManager;

    @Override
    public void save(CourseGrade entity) {
        entityManager.persist(entity);
    }

    @Override
    public void save(List<CourseGrade> entityList) {
        for (CourseGrade courseGrade : entityList) {
            save(courseGrade);
        }
    }

    @Override
    public CourseGrade merge(CourseGrade entity) {
        return entityManager.merge(entity);
    }

    @Override
    public List<CourseGrade> merge(List<CourseGrade> entityList) {
        List<CourseGrade> list = new ArrayList<>();
        for (CourseGrade courseGrade : entityList) {
            list.add(entityManager.merge(courseGrade));
        }
        return list;
    }

    @Override
    public void delete(CourseGrade entity) {
        if (entityManager.contains(entity)) {
            entityManager.remove(entity);
        } else {
            CourseGrade newEntity = entityManager.merge(entity);
            entityManager.remove(newEntity);
        }
    }

    @Override
    public void deleteById(Object id) {
        CourseGrade entity = entityManager.find(CourseGrade.class, id);
        entityManager.remove(entity);
    }

    @Override
    public Optional<CourseGrade> findById(Object id) {
        CourseGrade courseGrade = entityManager.find(CourseGrade.class, id);
        return Optional.ofNullable(courseGrade);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CourseGrade> findAll() {
        return (List<CourseGrade>) entityManager.createQuery("SELECT c FROM CourseGrade c").getResultList();
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}