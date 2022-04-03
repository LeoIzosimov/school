package com.jopov.schoolportal.common.models.schoolatribute;

import com.jopov.schoolportal.common.models.persons.Pupil;
import com.jopov.schoolportal.common.models.persons.Teacher;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name = "forms")
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte number;

    private String name;

    private String formName;

    @OneToMany(mappedBy = "form")
    private Set<Pupil> pupilSet;

    @OneToOne(mappedBy = "teacherForm")
    private Teacher formTeacher;

    private String profile;

}
