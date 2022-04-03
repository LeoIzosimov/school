package com.jopov.schoolportal.common.generator.persons;

import com.jopov.schoolportal.common.models.persons.Teacher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class TeacherGenerator {

    Random generator = new Random();
    private static final Path FILE_MALE = Path.of("data/MaleNames");
    private static final Path FILE_FEMALE = Path.of("data/FemaleNames");
    private static final Path FILE_SURNAME = Path.of("data/Surname");
    private static final Path FILE_PATRON_MALE = Path.of("data/MalePatronymic");
    private static final Path FILE_PATRON_FEMALE = Path.of("data/FemalePatronymic");


    public Teacher teacherGenerator() {
        boolean flag = generator.nextBoolean();
        String firstName = getFirstName(flag);
        String secondName = getSecondName(flag);
        String lastName = getLastName() + "а";
        String sex = flag ? "М" : "Ж";
        LocalDate birthday = randomDate();
        return new Teacher(firstName, secondName, lastName, sex, birthday);
    }

    private List<String> getFiles(Path path){
        try {
            return Files.lines(path).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getFirstName(boolean sex) {
        return getString(sex, FILE_MALE, FILE_FEMALE);
    }

    private String getSecondName(boolean sex) {
        return getString(sex, FILE_PATRON_MALE, FILE_PATRON_FEMALE);
    }

    private String getString(boolean sex, Path maleFile, Path femaleFile) {
        List<String> fileList = sex ? getFiles(maleFile) : getFiles(femaleFile);
        return fileList.get(generator.nextInt(fileList.size()));
    }

    private String getLastName() {
        List<String> firstName = getFiles(FILE_SURNAME);
        return firstName.get(generator.nextInt(firstName.size()));
    }

    private LocalDate randomDate() {
        LocalDate startDate = LocalDate.of(1957, 1, 1); // 2022 - 65 = 1957 Возраст когда люди на пенсию уходят
        long start = startDate.toEpochDay();
        LocalDate endDate = LocalDate.now();
        long end = endDate.toEpochDay();
        long randomDate = ThreadLocalRandom.current().nextLong(start, end);
        return LocalDate.ofEpochDay(randomDate);
    }

}
