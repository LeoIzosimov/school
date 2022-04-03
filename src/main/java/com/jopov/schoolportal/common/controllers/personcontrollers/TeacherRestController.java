package com.jopov.schoolportal.common.controllers.personcontrollers;

import com.jopov.schoolportal.common.controllers.personcontrollers.exception.PersonNotFoundException;
import com.jopov.schoolportal.common.models.persons.Teacher;
import com.jopov.schoolportal.common.services.persons.TeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.jopov.schoolportal.common.controllers.personcontrollers.ParamsRestController.APPLICATION_JSON_UTF8;
import static com.jopov.schoolportal.common.controllers.personcontrollers.ParamsRestController.FORMAT_MESSAGE_DELETE;

@RestController
@RequestMapping("/api/teachers")
public class TeacherRestController {

    public static final String URL_TEACHER = "/api/teachers";
    public static final String PERSON = "учитель";

    @Autowired
    private TeacherService service;

    @GetMapping("")
    @ApiOperation(
            value = "Вернуть список всех учителей",
            notes = "Возвращает список учителей с уникальными параметрами",
            response = Teacher.class,
            responseContainer = "List"
    )
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(ParamsRestController.APPLICATION_JSON_UTF8))
                .body(service.getAllTeachers());
    }

    @GetMapping("/{id}")
    @ApiOperation(
            value = "Вернуть учителя с заданным ID",
            notes = "Возвращает учителя с заданым идентификатором или бросает исключение PersonNotFoundException",
            response = Teacher.class
    )
    public ResponseEntity<Teacher> getTeacherById(
            @ApiParam(value = "Идентификатор учителя, которого нужно найти", required = true)
            @PathVariable Long id) throws PersonNotFoundException {
        Teacher teacher = service.getTeacherById(id);
        if (teacher == null) {
            throw new PersonNotFoundException(PERSON, id);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(teacher);
    }

    @PostMapping("")
    @ApiOperation(
            value = "Создать нового учителя",
            notes = "Создает учителя с заданными параметрами",
            response = Teacher.class
    )
    public ResponseEntity<Teacher> createTeacher(@RequestBody @Valid Teacher teacher) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(service.saveTeacher(teacher));
    }

    @PutMapping("")
    @ApiOperation(
            value = "Обновить существующего учителя",
            notes = "Изменяет параметры учителя или бросает исключение PersonNotFoundException",
            response = Teacher.class
    )
    public ResponseEntity<Teacher> updateTeacher(@RequestBody @Valid Teacher teacher) throws PersonNotFoundException {
        Long id = teacher.getId();
        Teacher oldTeacher = service.getTeacherById(id);
        if (oldTeacher == null) {
            throw new PersonNotFoundException(PERSON, id);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(service.editTeacher(teacher));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Удалить существущего учителя по ID",
            notes = "Удаляет учителя с заданым id или бросает исключение PersonNotFoundException",
            response = String.class
    )
    public ResponseEntity<String> deleteTeacherById(
            @ApiParam(value = "Идентификатор учителя, которого нужно найти", required = true)
            @PathVariable Long id) throws PersonNotFoundException {
        Teacher teacher = service.getTeacherById(id);
        if (teacher == null) {
            throw new PersonNotFoundException(PERSON, id);
        }
        service.deleteTeacherById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(String.format(FORMAT_MESSAGE_DELETE, PERSON, id));
    }
}
