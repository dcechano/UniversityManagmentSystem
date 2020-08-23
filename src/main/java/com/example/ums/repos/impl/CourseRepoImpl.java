package com.example.ums.repos.impl;

import com.example.ums.entities.Course;
import com.example.ums.repos.CourseRepo;
import org.springframework.stereotype.Repository;


@Repository
public class CourseRepoImpl extends AbstractRepoImpl<Course> implements CourseRepo {


    public CourseRepoImpl() {
        super(Course.class);
    }

}
