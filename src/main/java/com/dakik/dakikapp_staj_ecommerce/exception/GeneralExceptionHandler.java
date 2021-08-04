package com.dakik.dakikapp_staj_ecommerce.exception;

import java.time.Instant;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String PATH = "path";
    private static final String ERRORS = "error";
    private static final String STATUS = "status";
    private static final String TIMESTAMP = "timestamp";
    private static final String TYPE = "type";

    public static ResponseEntity<Object> getSuccessfulResponseEntity(final WebRequest r) {
        final Map<String, Object> body = new LinkedHashMap<>();
        ServletWebRequest request = (ServletWebRequest) r;
        body.put(TIMESTAMP, Instant.now());
        body.put(STATUS, HttpStatus.OK.value());
        body.put(PATH, request.getRequest().getRequestURI().toString());
        return new ResponseEntity<Object>(body, HttpStatus.OK);
    }

    private ResponseEntity<Object> getExceptionResponseEntity(final Throwable exception, final HttpStatus status,
            final WebRequest r, List<String> errors) {

        final Map<String, Object> body = new LinkedHashMap<>();
        ServletWebRequest request = (ServletWebRequest) r;
        body.put(TIMESTAMP, Instant.now());
        body.put(STATUS, status.value());
        body.put(ERRORS, errors);
        body.put(TYPE, exception.getClass().getSimpleName());
        body.put(PATH, request.getRequest().getRequestURI().toString());
        return new ResponseEntity<Object>(body, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ":" + error.getDefaultMessage()).collect(Collectors.toList());
        return getExceptionResponseEntity(ex, HttpStatus.BAD_REQUEST, request, errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
            WebRequest request) {
        return getExceptionResponseEntity(ex.getMostSpecificCause(), HttpStatus.CONFLICT, request,
                Collections.singletonList(ex.getMostSpecificCause().getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFound(NotFoundException ex, WebRequest request) {
        return getExceptionResponseEntity(ex, HttpStatus.NOT_FOUND, request,
                Collections.singletonList(ex.getMostSpecificCause().getMessage()));
    }

    @ExceptionHandler(AlreadyExistException.class)
    protected ResponseEntity<Object> handleAlreadyExist(AlreadyExistException ex, WebRequest request) {
        return getExceptionResponseEntity(ex, HttpStatus.CONFLICT, request,
                Collections.singletonList(ex.getMostSpecificCause().getMessage()));
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<Object> handleNotAuthorizedException(NotAuthorizedException ex, WebRequest request) {
        return getExceptionResponseEntity(ex, HttpStatus.UNAUTHORIZED, request,
                Collections.singletonList(ex.getMostSpecificCause().getMessage()));
    }
}