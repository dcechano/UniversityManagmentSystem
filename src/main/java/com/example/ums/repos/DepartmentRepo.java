package com.example.ums.repos;

import com.example.ums.entities.Department;

import java.util.Optional;

public interface DepartmentRepo extends AbstractRepo<Department> {

    Optional<Department> getDepartmentWithCourses(Long id);
    
}
