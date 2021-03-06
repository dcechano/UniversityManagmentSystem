package com.example.ums.enumstests.repotests;

import com.example.ums.config.RootConfig;
import com.example.ums.config.security.SecurityConfig;
import com.example.ums.config.web.WebConfig;
import com.example.ums.repos.DepartmentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class, WebConfig.class})
public class DepartmentRepoTest {

    @Autowired
    private DepartmentRepo repo;

    @BeforeEach
    void setup() {
        assertNotNull(repo);
    }


//    FIXME find out why the ApplicationContext won't load during test
//    @Test
//    public void getDepartmentWithCoursesTest() {
//        var department = repo.getDepartmentWithCourses(1L);
//        assertNotNull(department.getCourses());
//        assertEquals(3, department.getCourses().size());
//    }
}
