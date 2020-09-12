package com.example.ums.enumstests;

import com.example.ums.enums.AcademicStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestAcademicStatus {

    @Test
    public void testAcademicStatus() {
        AcademicStatus status = AcademicStatus.GRADUATED;
        assertEquals(status.getCode(), "G");
    }



}
