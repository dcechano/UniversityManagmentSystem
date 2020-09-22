package com.example.ums.enumstests.repotests;

import com.example.ums.config.RootConfig;
import com.example.ums.config.security.SecurityConfig;
import com.example.ums.config.web.WebConfig;
import com.example.ums.entities.person.impl.Student;
import com.example.ums.repos.StudentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebConfig.class, RootConfig.class, SecurityConfig.class})
public class StudentRepoTest {

    Logger logger = Logger.getLogger(getClass().toString());

    @Autowired
    StudentRepo studentRepo;

    @BeforeEach
    void beforeAll() {
        assertNotNull(studentRepo);
    }

    @Test
    public void findByIdPositive() {
        Optional<Student> op = studentRepo.findById(14L);

        op.ifPresentOrElse(
                p -> assertEquals(p.getFirstName(), "Dylan"),
                () -> fail("Optional was empty!")
        );
    }

    @Test
    public void findByIdNegative() {
        Optional<Student> op = studentRepo.findById(99L);
        assertThrows(NoSuchElementException.class,
                op::get);

    }

    @Test
    public void saveTest() {
        Student student = new Student();
        student.setEnrollmentDate(LocalDateTime.now());
        student.setFirstName("Naruto");
        student.setLastName("Uzumaki");
        student.setVersion(1);
        student.setModifiedAt(LocalDateTime.now());

        studentRepo.save(student);
        Optional<Student> op = studentRepo.findById(4L);
        op.ifPresentOrElse(
                p -> {
                    assertEquals(p.getId(), 4L);
                    assertEquals(p.getFirstName(), "Naruto");
                },

                () -> fail("Optional not present")
        );
    }

    @Test
    public void deletePositiveTest() {
        Optional<Student> student = studentRepo.findById(3L);
        student.ifPresentOrElse(
                p -> studentRepo.delete(p),
                () -> fail("Optional was empty!")
        );
        assertTrue(studentRepo.findById(3L).isEmpty());

    }

    @Test
    public void findAllTest() {
        List<Student> allStudents = studentRepo.findAll();
        assertNotNull(allStudents);
    }
}
