package com.jopov.schoolportal.common.controllers.personcontrollers;

import com.jopov.schoolportal.common.controllers.personcontrollers.exception.PersonNotFoundException;
import com.jopov.schoolportal.common.services.persons.PupilService;
import com.jopov.schoolportal.common.models.persons.Pupil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pupils")
public class PupilRestController {

    public static final String URL_PUPIL = "/api/pupils";
    public static final String PERSON = "ученик";

    @Autowired
    private PupilService service;


    @GetMapping()
    @ApiOperation(
            value = "Вернуть список всех учеников",
            notes = "Возвращает список учеников с уникальными параметрами",
            response = Pupil.class,
            responseContainer = "List"
    )
    public ResponseEntity<List<Pupil>> getAllPupils() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(ParamsRestController.APPLICATION_JSON_UTF8))
                .body(service.getAllPupils());
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(
            value = "Вернуть ученика с заданным ID",
            notes = "Возвращает ученика с заданым идентификатором или бросает исключение PersonNotFoundException",
            response = Pupil.class
    )
    public ResponseEntity<Pupil> getPupilById(
            @ApiParam(value = "Идентификатор ученика, которого нужно найти", required = true)
            @PathVariable Long id) throws PersonNotFoundException {
        Pupil pupil = service.getPupilById(id);
        if (pupil == null) {
            throw new PersonNotFoundException(PERSON, id);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(ParamsRestController.APPLICATION_JSON_UTF8))
                .body(pupil);
    }

    @PostMapping()
    @ApiOperation(
            value = "Создать нового ученика",
            notes = "Создает ученика с заданными параметрами",
            response = Pupil.class
    )
    public ResponseEntity<Pupil> createPupil(@RequestBody @Valid Pupil pupil) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.valueOf(ParamsRestController.APPLICATION_JSON_UTF8))
                .body(service.savePupil(pupil));
    }

    @PutMapping()
    @ApiOperation(
            value = "Обновить существующего ученика",
            notes = "Изменяет параметры ученика или бросает исключение PersonNotFoundException",
            response = Pupil.class
    )
    public ResponseEntity<Pupil> updatePupil(@RequestBody @Valid Pupil pupil) throws PersonNotFoundException {
        Long id = pupil.getId();
        Pupil oldPupil = service.getPupilById(id);
        if (oldPupil == null) {
            throw new PersonNotFoundException(PERSON, id);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(ParamsRestController.APPLICATION_JSON_UTF8))
                .body(service.editPupil(pupil));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Удалить существущего ученка по ID",
            notes = "Удаляет ученика с заданым id или бросает исключение PersonNotFoundException",
            response = String.class
    )
    public ResponseEntity<String> deletePupil(
            @ApiParam(value = "Идентификатор ученика, которого нужно найти", required = true)
            @PathVariable Long id) throws PersonNotFoundException {
        Pupil pupil = service.getPupilById(id);
        if (pupil == null) {
            throw new PersonNotFoundException(PERSON, id);
        }
        service.deletePupilById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(ParamsRestController.APPLICATION_JSON_UTF8))
                .body(String.format(ParamsRestController.FORMAT_MESSAGE_DELETE, PERSON, id));
    }

}
