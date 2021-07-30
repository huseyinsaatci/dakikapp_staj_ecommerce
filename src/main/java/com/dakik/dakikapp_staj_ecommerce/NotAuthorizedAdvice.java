package com.dakik.dakikapp_staj_ecommerce;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotAuthorizedAdvice {
    @ResponseBody
    @ExceptionHandler(NotAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String notAuthorizedExceptionHandler(NotAuthorizedException ex) {
        return ex.getMessage();
    }
}
