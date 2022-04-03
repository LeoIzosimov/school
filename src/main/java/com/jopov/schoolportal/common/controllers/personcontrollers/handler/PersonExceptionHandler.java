package com.jopov.schoolportal.common.controllers.personcontrollers.handler;

import com.jopov.schoolportal.common.controllers.personcontrollers.exception.BodyMessage;
import com.jopov.schoolportal.common.controllers.personcontrollers.exception.PersonForbiddenException;
import com.jopov.schoolportal.common.controllers.personcontrollers.exception.PersonNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

import static com.jopov.schoolportal.common.controllers.personcontrollers.ParamsRestController.APPLICATION_JSON_UTF8;

@ControllerAdvice
public class PersonExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String MESSAGE_BAD_JSON_FORMAT = "ERROR: Нарушен JSON формат";
    public static final String MESSAGE_BAD_JSON_DATA = "ERROR: Ошибка в JSON данных";
    public static final String FORMAT_MESSAGE_BAD_URI_PARAM = "ERROR: Параметру 'id' задано неверное значение '%s'. Допустимый тип 'Long'";

    /**
     * Обрабатывает исключения, когда в БД не найдена запись
     */
    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<BodyMessage> handlerException(
            PersonNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(new BodyMessage(ex.getMessage()));
    }

    /**
     * Обрабатывает исключение, когда получен запрос с нарушеным форматом JSON
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .status(status)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(new BodyMessage(MESSAGE_BAD_JSON_FORMAT, ex.getMessage()));
    }

    /**
     * Обрабатывает исключения, когда данные JSON не прошли валидацию
     * В pom.xml подключил spring-boot-starter-validation
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseEntity
                .status(status)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(new BodyMessage(MESSAGE_BAD_JSON_DATA, ex.getMessage(), errors));
    }

    /**
     * Обрабатывает исключения, когда в строке адреса задан неверный тип переменной
     * Например:
     *      Верное значение /api/users/2
     *      Неверное значение /api/users/abc
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        BodyMessage body = new BodyMessage();
        body.setMessage(String.format(FORMAT_MESSAGE_BAD_URI_PARAM, ex.getValue()));
        body.setDebug(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(body);
    }

    /**
     * Обрабатывает исключения, когда получен запрос для которого нет обработчиков
     * TODO перехватывается vaadin'ом, что делать?
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new BodyMessage("ERROR: Обработчик для запроса не существует", ex.getMessage()), status);
    }

    /**
     * Обрабатывает исключения, связанные с доступом к ресурсу.
     * TODO проверить!
     */
    public ResponseEntity<BodyMessage> handlerException(PersonForbiddenException ex) {
        BodyMessage message = new BodyMessage();
        message.setMessage(ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

    /**
     * Обрабатывает прочие исключения, не обработанные выше
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(new BodyMessage("Внутренняя ошибка", ex.getMessage()));
    }
}
