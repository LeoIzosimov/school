package com.jopov.schoolportal.common.repository.persons;

import com.jopov.schoolportal.common.models.persons.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
}
