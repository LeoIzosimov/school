package com.jopov.schoolportal.common.models.persons;

import com.jopov.schoolportal.common.models.schoolatribute.Form;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name = "teachers")
public class Teacher extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "form_id", referencedColumnName = "id")
    private Form teacherForm;

    public Teacher(String firstName, String secondName, String lastName, String sex, LocalDate birthday) {
        super(firstName, secondName, lastName, sex, birthday);
    }

    public Teacher(Long id, String firstName, String secondName, String lastName, String sex, LocalDate birthday) {
        super(firstName, secondName, lastName, sex, birthday);
        this.id = id;
    }

}