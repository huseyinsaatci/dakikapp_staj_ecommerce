package com.dakik.dakikapp_staj_ecommerce.service;

import java.util.Optional;

import com.dakik.dakikapp_staj_ecommerce.model.User;
import com.dakik.dakikapp_staj_ecommerce.repository.UserRepository;
import com.dakik.dakikapp_staj_ecommerce.util.ErrorResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository rep;

    private Object response;
    private HttpStatus statusCode;

    private boolean isEmailUnique(String email) {
        return rep.findByEmail(email).isEmpty();
    }

    private boolean isFullNameUnique(String firstName, String lastName) {
        return rep.findByFullName(firstName, lastName).isEmpty();
    }

    private boolean isPhoneNumberUnique(String phoneNumber) {
        return rep.findByPhoneNumber(phoneNumber).isEmpty();
    }

    public Iterable<User> getAllUsers() {
        return rep.findAll();
    }

    public ResponseEntity<Object> getUser(int id) {
        rep.findById(id).ifPresentOrElse(user -> {
            response = user;
            statusCode = HttpStatus.OK;
        }, () -> {
            response = new ErrorResponse("User not found");
            statusCode = HttpStatus.NOT_FOUND;
        });
        return new ResponseEntity<Object>(response, statusCode);
    }

    public ResponseEntity<Object> deleteUser(int id) {
        rep.findById(id).ifPresentOrElse(user -> {
            response = user;
            statusCode = HttpStatus.OK;
            rep.deleteById(id);
        }, () -> {
            response = new ErrorResponse("User not found");
            statusCode = HttpStatus.NOT_FOUND;
        });
        return new ResponseEntity<Object>(response, statusCode);
    }

    public ResponseEntity<Object> addUser(User u) {
        ErrorResponse error = new ErrorResponse("Duplicate user");
        boolean errorExist = false;
        if (!isEmailUnique(u.getEmail())) {
            error.addSubError("User already exist with email: " + u.getEmail());
            errorExist = true;
        }
        if (!isFullNameUnique(u.getFirstName(), u.getLastName())) {
            error.addSubError("User already exist with name: " + u.getFirstName() + " " + u.getLastName());
            errorExist = true;
        }
        if (!isPhoneNumberUnique(u.getPhoneNumber())) {
            error.addSubError("User already exist with phone number: " + u.getPhoneNumber());
            errorExist = true;
        }
        if (errorExist)
            return new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
        return new ResponseEntity<Object>(rep.save(u), HttpStatus.CREATED);
    }

    public ResponseEntity<Object> logIn(String email, String password, String passwordRetype) {
        ErrorResponse error = new ErrorResponse("Login error");
        Optional<User> user = rep.findByEmail(email);
        if (!password.equals(passwordRetype)) {
            error.addSubError("Password fields doesn't match");
        } else if (user.isEmpty() || !user.get().getPassword().equals(password)) {
            error.addSubError("Invalid email address or password");
        } else {
            return new ResponseEntity<Object>("Logged in successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<Object>(error, HttpStatus.UNAUTHORIZED);
    }
}