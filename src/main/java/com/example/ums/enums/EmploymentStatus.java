package com.example.ums.enums;

public enum EmploymentStatus{

    PART_TIME("P"),
    FULL_TIME("F"),
    TENURED("T"),
    SABBATICAL("S"),
    RETIRED("S");

    private String code;

    EmploymentStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
