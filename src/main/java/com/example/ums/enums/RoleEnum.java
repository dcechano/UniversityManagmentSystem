package com.example.ums.enums;

public enum RoleEnum {
    ROLE_STUDENT,
    ROLE_FACULTY_MEMBER,
    ROLE_STAFF_MEMBER,
    ROLE_ADMIN;

    RoleEnum() {
    }

    /*
        Cuts the ROLE_ out of ROLE_STUDENT. This makes using Spring Security
        a little cleaner.
    */
    public String simpleName() {
        return name().substring(5);
    }
}
