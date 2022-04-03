package com.jopov.schoolportal.common.mappers;

import com.jopov.schoolportal.common.models.enums.SchoolSubjects;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Getter
@Setter
public class SchoolSubjectsByClassGrade {

    private final Map<Integer, List<SchoolSubjects>> schoolSubjectsGradationMap = new HashMap<>();

    {
        schoolSubjectsGradationMap.put(1, List.of(SchoolSubjects.CLEANLY_WRITING,
                SchoolSubjects.READING, SchoolSubjects.HANDY_CRAFT,
                SchoolSubjects.NATURAL_HISTORY, SchoolSubjects.MATHS,
                SchoolSubjects.VOCAL, SchoolSubjects.DRAWING,
                SchoolSubjects.RUSSIAN_LANGUAGE, SchoolSubjects.PHYSICAL_EDUCATION));
        schoolSubjectsGradationMap.put(2, List.of(SchoolSubjects.READING,
                SchoolSubjects.HANDY_CRAFT, SchoolSubjects.NATURAL_HISTORY,
                SchoolSubjects.MATHS, SchoolSubjects.VOCAL, SchoolSubjects.DRAWING,
                SchoolSubjects.RUSSIAN_LANGUAGE, SchoolSubjects.PHYSICAL_EDUCATION,
                SchoolSubjects.NATIVE_LANGUAGE));
        schoolSubjectsGradationMap.put(3, List.of(SchoolSubjects.READING,
                SchoolSubjects.HANDY_CRAFT, SchoolSubjects.NATURAL_HISTORY,
                SchoolSubjects.MATHS, SchoolSubjects.VOCAL, SchoolSubjects.DRAWING,
                SchoolSubjects.RUSSIAN_LANGUAGE, SchoolSubjects.PHYSICAL_EDUCATION,
                SchoolSubjects.NATIVE_LANGUAGE));
        schoolSubjectsGradationMap.put(4, List.of(SchoolSubjects.READING,
                SchoolSubjects.HANDY_CRAFT, SchoolSubjects.NATURAL_HISTORY,
                SchoolSubjects.MATHS, SchoolSubjects.VOCAL, SchoolSubjects.DRAWING,
                SchoolSubjects.RUSSIAN_LANGUAGE, SchoolSubjects.PHYSICAL_EDUCATION,
                SchoolSubjects.NATIVE_LANGUAGE, SchoolSubjects.THEOLOGY_AND_ETHICS,
                SchoolSubjects.FOREIGN_LANGUAGE));
        schoolSubjectsGradationMap.put(5, List.of(SchoolSubjects.NATURAL_HISTORY,
                SchoolSubjects.MATHS, SchoolSubjects.VOCAL, SchoolSubjects.DRAWING,
                SchoolSubjects.RUSSIAN_LANGUAGE, SchoolSubjects.PHYSICAL_EDUCATION,
                SchoolSubjects.NATIVE_LANGUAGE, SchoolSubjects.THEOLOGY_AND_ETHICS,
                SchoolSubjects.FOREIGN_LANGUAGE, SchoolSubjects.CIVICS,
                SchoolSubjects.LOCAL_HISTORY, SchoolSubjects.HISTORY,
                SchoolSubjects.LITERATURE, SchoolSubjects.LIFE_SAFETY_FUNDAMENTALS,
                SchoolSubjects.TECHNOLOGY));
        schoolSubjectsGradationMap.put(6, List.of(SchoolSubjects.MATHS,
                SchoolSubjects.VOCAL, SchoolSubjects.DRAWING,
                SchoolSubjects.RUSSIAN_LANGUAGE, SchoolSubjects.PHYSICAL_EDUCATION,
                SchoolSubjects.NATIVE_LANGUAGE, SchoolSubjects.FOREIGN_LANGUAGE,
                SchoolSubjects.CIVICS, SchoolSubjects.LOCAL_HISTORY,
                SchoolSubjects.HISTORY, SchoolSubjects.LITERATURE,
                SchoolSubjects.LIFE_SAFETY_FUNDAMENTALS, SchoolSubjects.TECHNOLOGY,
                SchoolSubjects.GEOGRAPHY, SchoolSubjects.BIOLOGY,
                SchoolSubjects.COMPUTER_SCIENCE, SchoolSubjects.SOCIAL_SCIENCE));
        schoolSubjectsGradationMap.put(7, List.of(SchoolSubjects.VOCAL,
                SchoolSubjects.DRAWING, SchoolSubjects.RUSSIAN_LANGUAGE,
                SchoolSubjects.PHYSICAL_EDUCATION, SchoolSubjects.NATIVE_LANGUAGE,
                SchoolSubjects.FOREIGN_LANGUAGE, SchoolSubjects.CIVICS,
                SchoolSubjects.LOCAL_HISTORY, SchoolSubjects.HISTORY,
                SchoolSubjects.LITERATURE, SchoolSubjects.LIFE_SAFETY_FUNDAMENTALS,
                SchoolSubjects.TECHNOLOGY, SchoolSubjects.GEOGRAPHY,
                SchoolSubjects.BIOLOGY, SchoolSubjects.COMPUTER_SCIENCE,
                SchoolSubjects.SOCIAL_SCIENCE, SchoolSubjects.TECHNICAL_DRAWING,
                SchoolSubjects.ALGEBRA, SchoolSubjects.GEOMETRY,
                SchoolSubjects.PHYSICS));
        schoolSubjectsGradationMap.put(8, List.of(SchoolSubjects.DRAWING,
                SchoolSubjects.RUSSIAN_LANGUAGE, SchoolSubjects.PHYSICAL_EDUCATION,
                SchoolSubjects.NATIVE_LANGUAGE, SchoolSubjects.FOREIGN_LANGUAGE,
                SchoolSubjects.HISTORY, SchoolSubjects.LITERATURE,
                SchoolSubjects.LIFE_SAFETY_FUNDAMENTALS, SchoolSubjects.TECHNOLOGY,
                SchoolSubjects.GEOGRAPHY, SchoolSubjects.BIOLOGY,
                SchoolSubjects.COMPUTER_SCIENCE, SchoolSubjects.SOCIAL_SCIENCE,
                SchoolSubjects.TECHNICAL_DRAWING, SchoolSubjects.ALGEBRA,
                SchoolSubjects.GEOMETRY, SchoolSubjects.PHYSICS,
                SchoolSubjects.CHEMISTRY));
        schoolSubjectsGradationMap.put(9, List.of(SchoolSubjects.DRAWING,
                SchoolSubjects.RUSSIAN_LANGUAGE, SchoolSubjects.PHYSICAL_EDUCATION,
                SchoolSubjects.NATIVE_LANGUAGE, SchoolSubjects.FOREIGN_LANGUAGE,
                SchoolSubjects.HISTORY, SchoolSubjects.LITERATURE,
                SchoolSubjects.LIFE_SAFETY_FUNDAMENTALS, SchoolSubjects.TECHNOLOGY,
                SchoolSubjects.GEOGRAPHY, SchoolSubjects.BIOLOGY,
                SchoolSubjects.COMPUTER_SCIENCE, SchoolSubjects.SOCIAL_SCIENCE,
                SchoolSubjects.ALGEBRA, SchoolSubjects.GEOMETRY,
                SchoolSubjects.PHYSICS, SchoolSubjects.CHEMISTRY));
        schoolSubjectsGradationMap.put(10, List.of(SchoolSubjects.DRAWING,
                SchoolSubjects.RUSSIAN_LANGUAGE, SchoolSubjects.PHYSICAL_EDUCATION,
                SchoolSubjects.NATIVE_LANGUAGE, SchoolSubjects.FOREIGN_LANGUAGE,
                SchoolSubjects.HISTORY, SchoolSubjects.LITERATURE,
                SchoolSubjects.LIFE_SAFETY_FUNDAMENTALS, SchoolSubjects.TECHNOLOGY,
                SchoolSubjects.GEOGRAPHY, SchoolSubjects.BIOLOGY,
                SchoolSubjects.COMPUTER_SCIENCE, SchoolSubjects.SOCIAL_SCIENCE,
                SchoolSubjects.ALGEBRA, SchoolSubjects.GEOMETRY,
                SchoolSubjects.PHYSICS, SchoolSubjects.CHEMISTRY,
                SchoolSubjects.NATURAL_SCIENCE, SchoolSubjects.BASIC_ECONOMICS,
                SchoolSubjects.JURISPRUDENCE, SchoolSubjects.PHILOSOPHY,
                SchoolSubjects.ECOLOGY));
        schoolSubjectsGradationMap.put(11, List.of(SchoolSubjects.DRAWING,
                SchoolSubjects.RUSSIAN_LANGUAGE, SchoolSubjects.PHYSICAL_EDUCATION,
                SchoolSubjects.NATIVE_LANGUAGE, SchoolSubjects.FOREIGN_LANGUAGE,
                SchoolSubjects.HISTORY, SchoolSubjects.LITERATURE,
                SchoolSubjects.LIFE_SAFETY_FUNDAMENTALS, SchoolSubjects.TECHNOLOGY,
                SchoolSubjects.BIOLOGY, SchoolSubjects.COMPUTER_SCIENCE,
                SchoolSubjects.SOCIAL_SCIENCE, SchoolSubjects.ALGEBRA,
                SchoolSubjects.GEOMETRY, SchoolSubjects.PHYSICS,
                SchoolSubjects.CHEMISTRY, SchoolSubjects.NATURAL_SCIENCE,
                SchoolSubjects.BASIC_ECONOMICS, SchoolSubjects.JURISPRUDENCE,
                SchoolSubjects.PHILOSOPHY, SchoolSubjects.ECOLOGY,
                SchoolSubjects.ASTRONOMY, SchoolSubjects.BASIC_MILITARY_TRAINING,
                SchoolSubjects.WORLD_ART_CULTURE, SchoolSubjects.RHETORIC));
    }

    public List<SchoolSubjects> schoolSubjectsListByClassGrade(Integer classGrade) {
        if (classGrade != null && classGrade >= 1 && classGrade <= 11) {
            return new ArrayList<>(schoolSubjectsGradationMap.get(classGrade));
        } else throw new IllegalArgumentException("Номера классов с 1 по 11 включительно!");
    }
}
