package com.jopov.schoolportal.common.models.schoolatribute;

import com.jopov.schoolportal.common.models.persons.Teacher;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "date_lesson")
    private LocalDate dateLesson;

    @Column(name = "time_start")
    private LocalTime timeStart;

    @Column(name = "time_end")
    private LocalTime timeEnd;

    @Column(name = "number_classroom")
    private Integer numberClassroom;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "lesson_teacher",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private Set<Teacher> teachers;

    public Lesson(String subjectName, LocalDate dateLesson, LocalTime timeStart, LocalTime timeEnd, Integer numberClassroom) {
        this.subjectName = subjectName;
        this.dateLesson = dateLesson;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.numberClassroom = numberClassroom;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", subjectName='" + subjectName + '\'' +
                ", dateLesson=" + dateLesson +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                ", numberClassroom=" + numberClassroom +
                '}';
    }
}
