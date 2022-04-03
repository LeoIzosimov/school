package com.jopov.schoolportal.common.services.persons;

import com.jopov.schoolportal.common.models.persons.Teacher;

import java.util.List;

public interface TeacherService {
    public List<Teacher> getAllTeachers();
    public Teacher getTeacherById(Long id);
    public Teacher saveTeacher(Teacher teacher);
    public Teacher editTeacher(Teacher teacher);
    void deleteTeacherById(Long id);
}
