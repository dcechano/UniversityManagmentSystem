package com.example.ums.repos.impl;

import com.example.ums.entities.Department;
import com.example.ums.repos.DepartmentRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("unchecked")
@Repository
@Transactional
public class DepartmentRepoImpl extends AbstractRepoImpl<Department> implements DepartmentRepo {

    public DepartmentRepoImpl() {
        super(Department.class);
    }

    @Override
    public Optional<Department> getDepartmentWithCourses(Long id) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("department-entity-graph");
        Department department = entityManager.find(Department.class, id, Map.of("javax.persistence.fetchgraph", entityGraph));
        return Optional.ofNullable(department);
    }
}
