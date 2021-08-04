package com.dakik.dakikapp_staj_ecommerce.exception;

import org.springframework.core.NestedRuntimeException;

public class NotFoundException extends NestedRuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
