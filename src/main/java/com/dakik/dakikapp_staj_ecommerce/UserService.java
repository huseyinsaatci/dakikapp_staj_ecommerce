package com.dakik.dakikapp_staj_ecommerce;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

@Service
public class UserService {
    protected ResponseEntity<Object> validateUser(User u, String email, String password, WebRequest request) {
        if (u == null || !u.getPassword().equals(password))
            throw new NotAuthorizedException("Credentials uncorrect, E-mail:" + email);
        return GeneralExceptionHandler.getSuccessfulResponseEntity(request);
    }

    protected NotFoundException throwProductNotFoundException(int id) {
        return new NotFoundException("Could not find user, ID:" + id);
    }

    protected NotFoundException throwProductNotFoundException(String email) {
        return new NotFoundException("Could not find user, E-mail:" + email);
    }
}