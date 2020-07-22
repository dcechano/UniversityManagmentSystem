package com.example.ums.enums.converter;

import com.example.ums.enums.EmploymentStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class EmploymentStatusConverter implements AttributeConverter<EmploymentStatus, String> {

    @Override
    public String convertToDatabaseColumn(EmploymentStatus employmentStatus) {
        return employmentStatus == null ? null : employmentStatus.getCode();
    }

    @Override
    public EmploymentStatus convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(EmploymentStatus.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
