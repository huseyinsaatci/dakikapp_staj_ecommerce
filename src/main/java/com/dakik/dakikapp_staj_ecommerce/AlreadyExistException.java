package com.dakik.dakikapp_staj_ecommerce;

import org.springframework.core.NestedRuntimeException;

public class AlreadyExistException extends NestedRuntimeException {
    AlreadyExistException(String message) {
        super(message);
    }
}
