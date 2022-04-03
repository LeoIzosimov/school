package com.jopov.schoolportal.common.models.schoolatribute;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "homework")
public class HomeWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject_name")
    private String name;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "description")
    private String description;

    public HomeWork(String name, LocalDate date, String description) {
        this.date = date;
        this.name = name;
        this.description = description;
    }
}
