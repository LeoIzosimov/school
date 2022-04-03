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
@Table(name = "pupils")
public class Pupil extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="form_name", referencedColumnName="formName", nullable = false)
    private Form form;

    public Pupil(String firstName, String secondName, String lastName, String sex, LocalDate birthday) {
        super(firstName, secondName, lastName, sex, birthday);
    }

    public Pupil(Long id, String firstName, String secondName, String lastName, String sex, LocalDate birthday) {
        super(firstName, secondName, lastName, sex, birthday);
        this.id = id;
    }
}
