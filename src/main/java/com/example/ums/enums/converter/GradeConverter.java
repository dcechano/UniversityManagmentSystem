package com.example.ums.enums.converter;

import com.example.ums.enums.Grade;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class GradeConverter implements AttributeConverter<Grade, String> {

    @Override
    public String convertToDatabaseColumn(Grade grade) {
        return grade == null ? null : grade.getCode();
    }

    @Override
    public Grade convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(Grade.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
