package com.dakik.dakikapp_staj_ecommerce.service;

import com.dakik.dakikapp_staj_ecommerce.exception.GeneralExceptionHandler;
import com.dakik.dakikapp_staj_ecommerce.exception.NotAuthorizedException;
import com.dakik.dakikapp_staj_ecommerce.exception.NotFoundException;
import com.dakik.dakikapp_staj_ecommerce.model.User;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

@Service
public class UserService {
    public ResponseEntity<Object> validateUser(User u, String email, String password, WebRequest request) {
        if (u == null || !u.getPassword().equals(password))
            throw new NotAuthorizedException("Credentials uncorrect, E-mail:" + email);
        return GeneralExceptionHandler.getSuccessfulResponseEntity(request);
    }

    public NotFoundException throwProductNotFoundException(int id) {
        return new NotFoundException("Could not find user, ID:" + id);
    }

    public NotFoundException throwProductNotFoundException(String email) {
        return new NotFoundException("Could not find user, E-mail:" + email);
    }
}