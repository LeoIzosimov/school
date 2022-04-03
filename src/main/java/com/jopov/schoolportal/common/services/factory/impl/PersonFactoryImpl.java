package com.jopov.schoolportal.common.services.factory.impl;

import com.jopov.schoolportal.common.models.persons.Director;
import com.jopov.schoolportal.common.models.persons.Pupil;
import com.jopov.schoolportal.common.models.persons.Teacher;
import com.jopov.schoolportal.common.services.factory.PersonFactory;
import com.jopov.schoolportal.common.models.persons.Person;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PersonFactoryImpl implements PersonFactory {

    @Override
    public Person createPersonByPersonRole(Set<String> roleName) {
        Person person = null;

        for (String role : roleName) {
            switch (role) {
                case "ROLE_DIRECTOR":
                    person = new Director();
                    break;
                case "ROLE_HEAD_TEACHER":

                case "ROLE_TEACHER":
                    person = new Teacher();
                    break;
                default:
                    person = new Pupil();
                    break;
            }
        }
        return person;
    }
}