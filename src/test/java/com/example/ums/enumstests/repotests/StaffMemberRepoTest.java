package com.example.ums.enumstests.repotests;

import com.example.ums.entities.person.impl.StaffMember;
import com.example.ums.enumstests.testconfig.TestConfig;
import com.example.ums.repos.StaffMemberRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
public class StaffMemberRepoTest {

    @Autowired
    private StaffMemberRepo staffMemberRepo;

    @BeforeEach
    void beforeEach() {
        assertNotNull(staffMemberRepo);
    }

    @Test
    public void countTest() {
        long count = staffMemberRepo.count();
        assertEquals(2, count);
    }

    @Test
    @Rollback
    public void deleteTest() {
        Optional<StaffMember> deleted = staffMemberRepo.findById(2L);
        deleted.ifPresentOrElse(
                s -> staffMemberRepo.delete(s),
                () -> fail("Optional was empty!")
        );
        assertTrue(staffMemberRepo.findById(2L).isEmpty());
    }

    @Test
    @Rollback
    public void updateTest() {
        Optional<StaffMember> dill = staffMemberRepo.findById(1L);
        dill.ifPresentOrElse(
                s -> {
                    s.setFirstName("Dill");
                },
                () -> fail("Optional was empty!")
        );
        var updated = staffMemberRepo.merge(dill.get());
        assertEquals(updated.getFirstName(), "Dill");
    }

    @Test
    @Rollback
    public void deleteByIdPositive() {
        staffMemberRepo.deleteById(2L);
        Optional<StaffMember> deleted = staffMemberRepo.findById(2L);
        assertTrue(deleted.isEmpty());
    }

    @Test
    public void deleteByIdNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> staffMemberRepo.deleteById(99L));
    }

    @Test
    public void findAllTest() {
        List<StaffMember> list = staffMemberRepo.findAll();
        assertNotNull(list);
        assertEquals(2, list.size());
    }
}
