package com.dakik.dakikapp_staj_ecommerce.exception;

import org.springframework.core.NestedRuntimeException;

public class NotAuthorizedException extends NestedRuntimeException {
    public NotAuthorizedException(String message) {
        super(message);
    }
}
