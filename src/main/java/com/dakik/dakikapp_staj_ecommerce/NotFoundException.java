package com.dakik.dakikapp_staj_ecommerce;

import org.springframework.core.NestedRuntimeException;

public class NotFoundException extends NestedRuntimeException {
    NotFoundException(String message) {
        super(message);
    }
}
