package com.dakik.dakikapp_staj_ecommerce;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(int id) {
        super("User already exists: " + id);
    }

    public UserAlreadyExistException(String email) {
        super("User already exists: " + email);
    }
}