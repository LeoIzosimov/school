package com.jopov.schoolportal.common.services.persons;

import com.jopov.schoolportal.common.models.persons.Pupil;

import java.util.List;

public interface PupilService {
    public List<Pupil> getAllPupils();

    public Pupil getPupilById(Long id);

    public Pupil savePupil(Pupil pupil);

    public Pupil editPupil(Pupil pupil);

    public void deletePupilById(Long id);
}
