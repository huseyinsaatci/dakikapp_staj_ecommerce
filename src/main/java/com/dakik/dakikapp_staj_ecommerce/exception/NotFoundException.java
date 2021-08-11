package com.dakik.dakikapp_staj_ecommerce.exception;

import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity Not Found")
public class NotFoundException extends NestedRuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
