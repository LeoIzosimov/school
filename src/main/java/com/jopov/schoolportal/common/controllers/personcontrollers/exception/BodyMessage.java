package com.jopov.schoolportal.common.controllers.personcontrollers.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BodyMessage {
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String debug;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errors;

    public BodyMessage(String message) {
        this.message = message;
    }

    public BodyMessage(String message, String debug) {
        this.message = message;
        this.debug = debug;
    }
}
