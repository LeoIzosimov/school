package com.jopov.schoolportal.common.services.schooattribute.impl;

import com.jopov.schoolportal.common.models.schoolatribute.Form;
import com.jopov.schoolportal.common.repository.schooattribute.FormRepository;
import com.jopov.schoolportal.common.services.schooattribute.FormService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class FormServiceImpl implements FormService {

    private final FormRepository repository;

    public FormServiceImpl(FormRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Form> getAllForm() {
        return repository.findAll();
    }

    @Override
    public Form getFormById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void save(Form form) {
        repository.save(form);
    }

    @Override
    public void edit(Form form) {

        if (repository.findById(form.getId()).isPresent()) {
            repository.save(form);
        } else {
            throw new EntityNotFoundException("form id is empty or not found in the repository");
        }
    }

    @Override
    public void deleteFormById(Long id) {
        repository.deleteById(id);
    }
}
