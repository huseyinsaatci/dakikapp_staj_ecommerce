package com.dakik.dakikapp_staj_ecommerce;

import org.springframework.core.NestedRuntimeException;

public class NotAuthorizedException extends NestedRuntimeException {
    NotAuthorizedException(String message) {
        super(message);
    }
}
