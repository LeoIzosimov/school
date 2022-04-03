package com.jopov.schoolportal.personcontrollers.unit;

import com.jopov.schoolportal.common.controllers.personcontrollers.ParamsRestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jopov.schoolportal.common.models.persons.Person;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PersonTestMethods {

    public static boolean GET_checkAllPersons(MockMvc mockMvc, String url, List<? extends Person> persons, ObjectMapper objectMapper) throws Exception {
        mockMvc.perform(get(url).contentType(ParamsRestController.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(ParamsRestController.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].firstName", is("Леонид")))    //выборочная проверка
                .andExpect(jsonPath("$[1].sex", is("М")))
                .andExpect(jsonPath("$[2].sex", is("Ж")))
                .andExpect(content().json(objectMapper.writeValueAsString(persons))); //полное сравнение списка сущностей
        return true;
    }

    public static <P extends Person> boolean GET_checkOnePerson(MockMvc mockMvc, String url, P person, ObjectMapper objectMapper) throws Exception {
        mockMvc.perform(get(url).contentType(ParamsRestController.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(ParamsRestController.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(content().json(objectMapper.writeValueAsString(person)));//.andDo(print());
        return true;
    }

    public static <P extends Person> boolean POST_checkOnePerson(MockMvc mockMvc, String url, P person, ObjectMapper objectMapper) throws Exception {
        mockMvc.perform(post(url).contentType(ParamsRestController.APPLICATION_JSON_UTF8)
                                .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(ParamsRestController.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(content().json(objectMapper.writeValueAsString(person)));
        return true;
    }

    public static <P extends Person> boolean PUT_checkOnePerson(MockMvc mockMvc, String url, P person, ObjectMapper objectMapper) throws Exception {
        mockMvc.perform(put(url).contentType(ParamsRestController.APPLICATION_JSON_UTF8)
                                .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(ParamsRestController.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(content().json(objectMapper.writeValueAsString(person)));
        return true;
    }

    public static boolean DELETE_checkOnePerson(MockMvc mockMvc, String url, String messageSample) throws Exception {
        mockMvc.perform(delete(url).contentType(ParamsRestController.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(ParamsRestController.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").value(messageSample));
        return true;
    }

    public static boolean GET_notFoundPerson(MockMvc mockMvc, String url, String messageSample) throws Exception {
        mockMvc.perform(get(url).contentType(ParamsRestController.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(ParamsRestController.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("message").value(messageSample));
        return true;
    }

    public static <P extends Person> boolean PUT_notFoundPerson(MockMvc mockMvc, String url, P person, String messageSample, ObjectMapper objectMapper) throws Exception {
        mockMvc.perform(put(url).contentType(ParamsRestController.APPLICATION_JSON_UTF8)
                                .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(ParamsRestController.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("message").value(messageSample));
        return true;
    }

    public static boolean DELETE_notFoundPerson(MockMvc mockMvc, String url, String messageSample) throws Exception {
        mockMvc.perform(delete(url).contentType(ParamsRestController.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(ParamsRestController.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("message").value(messageSample));
        return true;
    }

    public static boolean GET_badUriParam(MockMvc mockMvc, String url, String messageSample) throws Exception {
        mockMvc.perform(get(url).contentType(ParamsRestController.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(ParamsRestController.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("message").value(messageSample))
                .andExpect(jsonPath("debug", notNullValue()));
        return true;
    }

    public static boolean POST_badJsonFormat(MockMvc mockMvc, String url, String badJsonFormat, String messageSample) throws Exception {
        mockMvc.perform(post(url).contentType(ParamsRestController.APPLICATION_JSON_UTF8)
                                .content(badJsonFormat))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(ParamsRestController.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("message").value(messageSample))
                .andExpect(jsonPath("debug", notNullValue()));
        return true;
    }

    public static boolean POST_badJsonData(MockMvc mockMvc, String url, String badJsonData, String messageSample) throws Exception {
        mockMvc.perform(post(url).contentType(ParamsRestController.APPLICATION_JSON_UTF8)
                                .content(badJsonData))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(ParamsRestController.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("message").value(messageSample))
                .andExpect(jsonPath("debug", notNullValue()))
                .andExpect(jsonPath("errors", notNullValue()));
        return true;
    }
}
