package com.dakik.dakikapp_staj_ecommerce;

public class UserNotFoundException extends RuntimeException {
    UserNotFoundException(int id) {
        super("Could not find user: " + id);
    }
}