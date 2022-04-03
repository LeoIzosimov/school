package com.jopov.schoolportal.common.models.persons;

import com.jopov.schoolportal.common.models.persons.validate.SizeNotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

@MappedSuperclass
public abstract class Person {

    @SizeNotBlank(min = 2, message = "Поле firstName минимум 2 символа")
    private String firstName;
    @SizeNotBlank(min = 2, message = "Поле secondName минимум 2 символа")
    private String secondName;
    @SizeNotBlank(min = 2, message = "Поле lastName минимум 2 символа")
    private String lastName;
    @Pattern(regexp="[МЖ]", message = "Поле sex 1 символ: М или Ж")
    private String sex;
    //TODO Валидация даты отложена до уточнения реализации
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd")
    private LocalDate birthday;
}
