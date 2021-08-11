package com.dakik.dakikapp_staj_ecommerce.exception;

import com.dakik.dakikapp_staj_ecommerce.util.ErrorResponse;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler {
    // @Override
    // protected ResponseEntity<Object>
    // handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
    // HttpHeaders headers, HttpStatus status, WebRequest request) {
    // ErrorResponse error = new ErrorResponse("Request Invalid");
    // ex.getBindingResult().getFieldErrors().stream().map(err ->
    // (err.getDefaultMessage())).forEach(e -> {
    // error.addSubError(e);
    // });
    // return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    // }

    // @ExceptionHandler(DataIntegrityViolationException.class)
    // protected ResponseEntity<Object>
    // handleDataIntegrityViolation(DataIntegrityViolationException ex,
    // WebRequest request) {
    // return getExceptionResponseEntity(ex.getMostSpecificCause(),
    // HttpStatus.CONFLICT, request,
    // Collections.singletonList(ex.getMostSpecificCause().getMessage()));
    // }
}