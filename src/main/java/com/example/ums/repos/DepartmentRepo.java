package com.example.ums.repos;

import com.example.ums.entities.Course;
import com.example.ums.entities.Department;

import java.util.List;

public interface DepartmentRepo extends AbstractRepo<Department> {

    Department getDepartmentWithCourses(Long id);


}
