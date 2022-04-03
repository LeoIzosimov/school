package com.jopov.schoolportal.common.generator.persons;

import com.jopov.schoolportal.common.models.enums.Grade;
import com.jopov.schoolportal.common.models.persons.Pupil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class PupilGenerator {
    Random generator = new Random();
    private static File fileMale;
    private static File fileFemale;
    private static File fileSurname;
    private static File filePatronMale;
    private static File filePatronFemale;

    static {
        try {
            fileMale = new ClassPathResource("data/MaleNames").getFile();
            fileFemale = new ClassPathResource("data/FemaleNames").getFile();
            fileSurname = new ClassPathResource("data/Surname").getFile();
            filePatronMale = new ClassPathResource("data/MalePatronymic").getFile();
            filePatronFemale = new ClassPathResource("data/FemalePatronymic").getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pupil generatePupilByGrade(Grade grade) {
        return generatePupil(grade);
    }

    private Pupil generatePupil(Grade grade) {
        Map<String, String> mapName = getFirstName();
        Object[] names = mapName.keySet().toArray();
        String firstName = names[generator.nextInt(names.length)].toString();
        String sex = mapName.get(firstName);
        String secondName = sex.equalsIgnoreCase("М") ? getSecondName() : getSecondName() + "a";
        String lastName = sex.equalsIgnoreCase("М") ? getLastNameMale() : getLastNameFemale();
        LocalDate birthday = getDate(grade);

        return new Pupil(firstName, secondName, lastName, sex, birthday);
    }

    private Map<String, String> getFirstName() {

        List<String> nameListMale = getNamesFromFiles(fileMale);
        List<String> nameListFeMale = getNamesFromFiles(fileFemale);
        Map<String, String> namesMap = new HashMap<>();

        for (String name : nameListMale) {
            namesMap.put(name, "М");
        }
        for (String name : nameListFeMale) {
            namesMap.put(name, "Ж");
        }

        return namesMap;
    }

    private String getSecondName() {
        List<String> nameList = getNamesFromFiles(fileSurname);
        return nameList.get(generator.nextInt(nameList.size()));
    }

    private String getLastNameMale() {
        List<String> nameList = getNamesFromFiles(filePatronMale);
        return nameList.get(generator.nextInt(nameList.size()));
    }

    private String getLastNameFemale() {
        List<String> nameList = getNamesFromFiles(filePatronFemale);
        return nameList.get(generator.nextInt(nameList.size()));

    }

    private LocalDate getDate(Grade grade) {
        String[] rangeAge = grade.getValue().split("-");

        LocalDate dateNow = LocalDate.now();
        LocalDate dateStart = dateNow.minusYears(Integer.parseInt(rangeAge[1]));
        LocalDate dateEnd = dateNow.minusYears(Integer.parseInt(rangeAge[0]));

        return randomDate(dateStart, dateEnd);
    }

    private List<String> getNamesFromFiles(File file) {
        List<String> nameList = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                nameList.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return nameList;
    }

    private LocalDate randomDate(LocalDate startDate, LocalDate endDate) {
        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = endDate.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }

}