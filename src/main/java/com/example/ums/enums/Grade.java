package com.example.ums.enums;

public enum Grade {
    A("A"), AMinus("A-"),
    B("B"), BMinus("B-"),
    C("C"), CMinus("C-"),
    D("D"), DMinus("D-"),
    E("E");

    private String code;

    Grade(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
