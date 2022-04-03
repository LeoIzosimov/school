package com.jopov.schoolportal.common.controllers.personcontrollers.exception;

public class PersonForbiddenException extends RuntimeException{
    public PersonForbiddenException(String message) {
        super("Доступ запрещен. Выполните аутентификацию." + message);
    }
}
