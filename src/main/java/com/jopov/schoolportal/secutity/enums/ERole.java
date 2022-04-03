package com.jopov.schoolportal.secutity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ERole {

    ROLE_DIRECTOR("Директор"),
    ROLE_HEAD_TEACHER("Заведующий учебной частью"),
    ROLE_TEACHER("Учитель"),
    ROLE_PUPIL("Ученик");

    private final String value;
}
