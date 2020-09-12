package com.example.ums.enumstests.repotests;

import com.example.ums.config.RootConfig;
import com.example.ums.entities.Department;
import com.example.ums.entities.person.impl.FacultyMember;
import com.example.ums.repos.FacultyMemberRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
public class FacultyMemberRepoTest {

    @Autowired
    FacultyMemberRepo facultyMemberRepo;

    @BeforeEach
    void beforeEach() {
        assertNotNull(facultyMemberRepo);
    }

    @Test
    void testFindById() {
        Optional<FacultyMember> facultyMember = facultyMemberRepo.findById(1L);
        facultyMember.ifPresentOrElse(
                p -> {
                    assertEquals(p.getFirstName(), "David");
                    assertEquals(p.getDepartment().getName(), "Mathematics");
                },
                Assertions::fail);
    }

    @Test
    public void saveTest() {
        FacultyMember member = new FacultyMember();
        member.setFirstName("Test");
        member.setLastName("This is a test");
        Department department = new Department();
        department.setName("Test");
        member.setDepartment(department);
        facultyMemberRepo.save(member);

        Optional<FacultyMember> newMember = facultyMemberRepo.findById(2L);
        assertTrue(newMember.isPresent());
        assertEquals(newMember.get().getFirstName(), "Test");
    }

    @Test
    public void updateTest() {
        Optional<FacultyMember> walterJr = facultyMemberRepo.findById(2L);
        walterJr.ifPresentOrElse(
                f -> f.setLastName("White Jr"),
                () -> fail("Optional was empty!")
        );
        var updated = facultyMemberRepo.merge(walterJr.get());
        assertEquals(updated.getLastName(), "White Jr");
    }

    @Test
    public void deleteByIdPositive() {
        facultyMemberRepo.deleteById(2L);
        Optional<FacultyMember> deleted = facultyMemberRepo.findById(2L);
        assertTrue(deleted.isEmpty());
    }

    @Test
    public void deleteByIdNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> facultyMemberRepo.deleteById(99L));

    }

    @Test
    public void findAllTest() {
//        Test should not be run after the delete tests. Otherwise test will fail
        List<FacultyMember> list = facultyMemberRepo.findAll();
        assertNotNull(list);
        assertEquals(list.size(), 2);
    }

    @Test
    public void countTest() {
//        Test should not be run after the delete tests. Otherwise test will fail
        long count = facultyMemberRepo.count();
        assertEquals(count, 2);
    }

}
