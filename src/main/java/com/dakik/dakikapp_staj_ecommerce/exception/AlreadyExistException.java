package com.dakik.dakikapp_staj_ecommerce.exception;

import org.springframework.core.NestedRuntimeException;

public class AlreadyExistException extends NestedRuntimeException {
    public AlreadyExistException(String message) {
        super(message);
    }
}
