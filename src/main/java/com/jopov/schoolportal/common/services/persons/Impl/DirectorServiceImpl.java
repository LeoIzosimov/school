package com.jopov.schoolportal.common.services.persons.Impl;

import com.jopov.schoolportal.common.models.persons.Director;
import com.jopov.schoolportal.common.repository.persons.DirectorRepository;
import com.jopov.schoolportal.common.services.persons.DirectorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;

    public DirectorServiceImpl(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Override
    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    @Override
    public Director getDirectorById(Long id) {
        return directorRepository.findById(id).orElse(null);
    }

    @Override
    public Director saveDirector(Director director) {
        return directorRepository.save(director);
    }

    @Override
    public Director editDirector(Director director) {
        return directorRepository.save(director);
    }

    @Override
    public void deleteDirectorById(Long id) {
        directorRepository.deleteById(id);
    }
}
