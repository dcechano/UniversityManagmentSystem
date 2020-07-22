package com.example.ums.enums;

public enum AcademicStatus {

    GRADUATED("G"),
    GOOD_STANDING("S"),
    ACADEMIC_PROBATION("P"),
    NOT_ENROLLED("N");

    private String code;

    AcademicStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
