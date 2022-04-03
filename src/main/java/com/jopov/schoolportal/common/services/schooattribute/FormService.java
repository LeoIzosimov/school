package com.jopov.schoolportal.common.services.schooattribute;

import com.jopov.schoolportal.common.models.schoolatribute.Form;

import java.util.List;

public interface FormService {
    List<Form> getAllForm();
    Form getFormById(Long id);
    void save(Form form);
    void edit(Form form);
    void deleteFormById(Long id);
}
