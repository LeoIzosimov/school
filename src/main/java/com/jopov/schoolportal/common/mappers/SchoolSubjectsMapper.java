package com.jopov.schoolportal.common.mappers;

import com.jopov.schoolportal.common.models.enums.SchoolSubjects;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class SchoolSubjectsMapper implements AttributeConverter<SchoolSubjects, String> {

    @Override
    public String convertToDatabaseColumn(SchoolSubjects schoolSubjects) {
        if (schoolSubjects == null) {
            return null;
        }

        return schoolSubjects.getValue();
    }

    @Override
    public SchoolSubjects convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }

        return Stream.of(SchoolSubjects.values())
                .filter(v -> v.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
