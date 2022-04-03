package com.jopov.schoolportal.common.models.enums;

public enum Grade {
    ONE("6-8"),
    TWO("8-9"),
    THREE("9-10"),
    FOUR("10-11"),
    FIVE("11-12"),
    SIX("12-13"),
    SEVEN("13-14"),
    EIGHT("14-15"),
    NINE("15-16"),
    TEN("16-17"),
    ELEVEN("17-18"),
    ALL("6-18");

    private final String value;

    Grade(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
