package com.example.ums.enums.converter;

import com.example.ums.enums.AcademicStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class AcademicStatusConverter implements AttributeConverter<AcademicStatus, String> {

    @Override
    public String convertToDatabaseColumn(AcademicStatus academicStatus) {
        return academicStatus == null ? null : academicStatus.getCode();
    }

    @Override
    public AcademicStatus convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(AcademicStatus.values())
                .filter(s -> s.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
