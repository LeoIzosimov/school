package com.jopov.schoolportal.common.models.enums;

public enum Profile {

    PHYSICAL_MATHEMATICAL("Физико-математический"),
    CHEMICAL_BIOLOGICAL("Химико-биологический"),
    INFORMATION_TECHNOLOGICAL("Информационно-технологический"),
    SOCIO_ECONOMIC("Социально-экономический"),
    HUMANITARIAN("Гуманитарный"),
    LINGUISTIC("Лингвистический"),
    ARTISTICALLY_AESTHETIC("Художественно-эстетический");

    private final String value;

    Profile(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + value + '\'' +
                '}';
    }
}
