package com.jopov.schoolportal.common.mappers;

import com.jopov.schoolportal.common.models.enums.Profile;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true) //Автоматически применяется к полям типа Profile
public class ProfileMapper implements AttributeConverter<Profile, String> {

    @Override
    public String convertToDatabaseColumn(Profile profile) {
        if (profile == null) {
            return null;
        }
        return profile.getValue();
    }

    @Override
    public Profile convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return Stream.of(Profile.values())
                .filter(v -> v.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
