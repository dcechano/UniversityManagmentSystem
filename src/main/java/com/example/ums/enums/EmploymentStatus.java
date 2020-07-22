package com.example.ums.enums;

import com.example.ums.enums.converter.EmploymentStatusConverter;

import javax.persistence.AttributeConverter;

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

    public static EmploymentStatus getConstant(String code) {
        AttributeConverter<EmploymentStatus, String> converter = new EmploymentStatusConverter();
        return converter.convertToEntityAttribute(code);
    }
}
