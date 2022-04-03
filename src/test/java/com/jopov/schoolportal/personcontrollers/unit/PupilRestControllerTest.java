package com.jopov.schoolportal.personcontrollers.unit;

import com.jopov.schoolportal.common.controllers.personcontrollers.ParamsRestController;
import com.jopov.schoolportal.common.controllers.personcontrollers.PupilRestController;
import com.jopov.schoolportal.common.controllers.personcontrollers.handler.PersonExceptionHandler;
import com.jopov.schoolportal.common.services.persons.PupilService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jopov.schoolportal.common.models.persons.Pupil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/* Модульное тестирование *//* Интеграционное позднее */

@WebMvcTest(PupilRestController.class)
class PupilRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private PupilService service;

    private final Pupil pupil1 = new Pupil(1L, "Леонид", "Жопович", "Жопов", "М", LocalDate.of(1987,12,24));
    private final Pupil pupil2 = new Pupil(2L, "Паслен", "Облепихович", "Брусников", "М", LocalDate.of(1985,3,26));
    private final Pupil pupil3 = new Pupil(3L, "Бузина", "Кизиловна", "Фейхоа", "Ж", LocalDate.of(1981,8,6));


    @DisplayName("Test Norma. GET=>/api/pupils=>200.JSON(List<Pupil>)")
    @Test
    void GET_200_getAllPupils() throws Exception {
        List<Pupil> pupils = new ArrayList<>(Arrays.asList(pupil1, pupil2, pupil3));
        Mockito.when(service.getAllPupils()).thenReturn(pupils);
        Assertions.assertTrue(PersonTestMethods.GET_checkAllPersons(mockMvc, PupilRestController.URL_PUPIL, pupils, objectMapper));
    }

    @DisplayName("Test Norma. GET=>/api/pupils/1=>200.JSON(Pupil)")
    @Test
    void GET_200_getOnePupil() throws Exception {
        Mockito.when(service.getPupilById(1L)).thenReturn(pupil1);
        Assertions.assertTrue(PersonTestMethods.GET_checkOnePerson(mockMvc, PupilRestController.URL_PUPIL + "/1", pupil1, objectMapper));
    }

    @DisplayName("Test Norma. POST.JSON(Pupil)=>/api/pupils=>201.JSON(Pupil)")
    @Test
    void POST_201_createPupil() throws Exception {
        Mockito.when(service.savePupil(Mockito.any())).thenReturn(pupil1);
        Assertions.assertTrue(PersonTestMethods.POST_checkOnePerson(mockMvc, PupilRestController.URL_PUPIL, pupil1, objectMapper));
    }

    @DisplayName("Test Norma. PUT.JSON(Pupil)=>/api/pupils=>200.JSON(Pupil)")
    @Test
    void PUT_200_updatePupil() throws Exception {
        Mockito.when(service.getPupilById(Mockito.any())).thenReturn(pupil2);
        Mockito.when(service.editPupil(Mockito.any())).thenReturn(pupil2);
        Assertions.assertTrue(PersonTestMethods.PUT_checkOnePerson(mockMvc, PupilRestController.URL_PUPIL, pupil2, objectMapper));
    }

    @DisplayName("Test Norma. DELETE=>/api/pupils/3=>200.'Deleted'")
    @Test
    void DELETE_200_deletePupil() throws Exception {
        Long id = 3L;
        Mockito.when(service.getPupilById(Mockito.any())).thenReturn(pupil3);
        Assertions.assertTrue(PersonTestMethods.DELETE_checkOnePerson(mockMvc, PupilRestController.URL_PUPIL + "/" + id, String.format(ParamsRestController.FORMAT_MESSAGE_DELETE, PupilRestController.PERSON, id)));
    }


    @DisplayName("Test Error. GET=>/api/pupils/4=>404.'Not Found'")
    @Test
    void GET_404_getOnePupil() throws Exception {
        Long id = 4L;
        Mockito.when(service.getPupilById(id)).thenReturn(null);
        Assertions.assertTrue(PersonTestMethods.GET_notFoundPerson(mockMvc, PupilRestController.URL_PUPIL + "/" + id, String.format(ParamsRestController.FORMAT_MESSAGE_NOT_FOUND, PupilRestController.PERSON, id)));
    }

    @DisplayName("Test Error. PUT.JSON(Pupil)=>/api/pupils=>404.'Not Found'")
    @Test
    void PUT_404_updatePupil() throws Exception {
        Long id = 3L;
        Mockito.when(service.getPupilById(id)).thenReturn(null);
        Assertions.assertTrue(PersonTestMethods.PUT_notFoundPerson(mockMvc, PupilRestController.URL_PUPIL, pupil3, String.format(ParamsRestController.FORMAT_MESSAGE_NOT_FOUND, PupilRestController.PERSON, id), objectMapper));
    }

    @DisplayName("Test Error. DELETE=>/api/pupils/4=>404.'Not Found'")
    @Test
    void DELETE_404_deletePupil() throws Exception {
        Long id = 4L;
        Mockito.when(service.getPupilById(id)).thenReturn(null);
        Assertions.assertTrue(PersonTestMethods.DELETE_notFoundPerson(mockMvc, PupilRestController.URL_PUPIL + "/" + id, String.format(ParamsRestController.FORMAT_MESSAGE_NOT_FOUND, PupilRestController.PERSON, id)));
    }


    @DisplayName("Test Error. GET=>/api/pupils/Q=>400.'Bad id'")
    @Test
    void GET_400_badUriParam() throws Exception {
        String id = "Q";
        Assertions.assertTrue(PersonTestMethods.GET_badUriParam(mockMvc, PupilRestController.URL_PUPIL + "/" + id, String.format(PersonExceptionHandler.FORMAT_MESSAGE_BAD_URI_PARAM, id)));
    }


    @DisplayName("Test Error. POST.JSON(badJsonFormat)=>/api/pupils=>400.'Bad JSON format'")
    @Test
    void JSON_400_badJsonFormat() throws Exception {
        //после поля FirstName отсутствует символ ":"
        String badJsonFormat = "{\"firstName\" \"fn\", \"secondName\": \"sn\", \"lastName\": \"ln\", \"sex\": \"М\", \"birthday\": \"2000-02-02\"}";
        Assertions.assertTrue(PersonTestMethods.POST_badJsonFormat(mockMvc, PupilRestController.URL_PUPIL, badJsonFormat, PersonExceptionHandler.MESSAGE_BAD_JSON_FORMAT));
    }

    @DisplayName("Test Error. POST.JSON(badJsonData)=>/api/pupils=>400.'Bad JSON data'")
    @Test
    void JSON_400_badJsonData() throws Exception {
        //полям *name заданы короткие строки, полю sex задан недопустимый символ
        String badJsonData = "{\"firstName\": \"a\", \"secondName\": \"b\", \"lastName\": \"c\", \"sex\": \"d\", \"birthday\": \"2000-02-02\"}";
        Assertions.assertTrue(PersonTestMethods.POST_badJsonData(mockMvc, PupilRestController.URL_PUPIL, badJsonData, PersonExceptionHandler.MESSAGE_BAD_JSON_DATA));
    }

}