package com.jopov.schoolportal.common.models.enums;

public enum SchoolSubjects {

    CLEANLY_WRITING("Чистописание"),
    READING("Чтение"),
    HANDY_CRAFT("Труд"),
    NATURAL_HISTORY("Природоведение"),
    MATHS("Математика"),
    VOCAL("Пение"),
    DRAWING("Рисование"),
    PHYSICAL_EDUCATION("Физкультура"),
    RUSSIAN_LANGUAGE("Русский язык"),
    NATIVE_LANGUAGE("Родной язык"),
    FOREIGN_LANGUAGE("Иностранный язык"),
    THEOLOGY_AND_ETHICS("Основы религиозных культур и светской этики"),
    CIVICS("Граждановедение"),
    LOCAL_HISTORY("Краеведение"),
    HISTORY("История"),
    LITERATURE("Литература"),
    LIFE_SAFETY_FUNDAMENTALS("ОБЖ"),
    TECHNOLOGY("Технология"),
    GEOGRAPHY("География"),
    BIOLOGY("Биология"),
    COMPUTER_SCIENCE("Информатика"),
    SOCIAL_SCIENCE("Обществознание"),
    TECHNICAL_DRAWING("Черчение"),
    ALGEBRA("Алгебра"),
    GEOMETRY("Геометрия"),
    PHYSICS("Физика"),
    CHEMISTRY("Химия"),
    NATURAL_SCIENCE("Естествознание"),
    BASIC_ECONOMICS("Основы экономика"),
    JURISPRUDENCE("Юриспруденция"),
    PHILOSOPHY("Философия"),
    ECOLOGY("Экология"),
    ASTRONOMY("Астрономия"),
    BASIC_MILITARY_TRAINING("Начальная военная подготовка"),
    WORLD_ART_CULTURE("Мировая художественная культура"),
    RHETORIC("Реторика");

    private final String value;

    SchoolSubjects(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
