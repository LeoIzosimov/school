package com.jopov.schoolportal.common.services.factory;

import com.jopov.schoolportal.common.models.persons.Person;

import java.util.Set;

public interface PersonFactory {
    Person createPersonByPersonRole(Set<String> roleName);
}