package com.example.ums.repos.impl;

import com.example.ums.entities.Course;
import com.example.ums.entities.Department;
import com.example.ums.entities.person.impl.Student;
import com.example.ums.repos.DepartmentRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("unchecked")
@Repository
@Transactional
public class DepartmentRepoImpl implements DepartmentRepo {

    private EntityManager entityManager;

    @Override
    public Department getDepartmentWithCourses(Long id) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("department-entity-graph");
        return entityManager.find(Department.class, id, Map.of("javax.persistence.fetchgraph", entityGraph));
    }

    @Override
    public void save(Department entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Department entity) {
        if (entityManager.contains(entity)) {
            entityManager.remove(entity);
        } else {
            var newEntity = entityManager.merge(entity);
            entityManager.remove(newEntity);
        }
    }

    @Override
    public Department update(Department entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void deleteById(Long id) {
        Department department = entityManager.find(Department.class, id);
        entityManager.remove(department);
    }

    @Override
    public Optional<Department> findById(Long id) {
        Department department = entityManager.find(Department.class, id);
        return Optional.ofNullable(department);
    }

    @Override
    public List<Department> findAll() {
        return (List<Department>) entityManager.createQuery("SELECT d FROM Department d").getResultList();
    }

    @Override
    public Map<Long, Department> findAllAsMap() {
        return null;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
