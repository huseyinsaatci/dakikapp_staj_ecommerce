package com.dakik.dakikapp_staj_ecommerce.exception;

import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "User Not Authorized")
public class NotAuthorizedException extends NestedRuntimeException {
    public NotAuthorizedException(String message) {
        super(message);
    }
}
