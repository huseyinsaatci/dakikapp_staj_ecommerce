package com.dakik.dakikapp_staj_ecommerce;

public class NotAuthorizedException extends RuntimeException {
    NotAuthorizedException(int id) {
        super("Credentials uncorrect: " + id);
    }
}
