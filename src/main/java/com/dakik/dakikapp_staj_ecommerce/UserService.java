package com.dakik.dakikapp_staj_ecommerce;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public boolean checkIfUserAlreadyExist(User u, UserRepository rep) {
        return rep.findByEmail(u.getEmail()).orElseThrow(() -> new UserAlreadyExistException(u.getEmail())) != null;
    }

    public void validateUser(User u, String password) {
        if (u == null || !u.getPassword().equals(password))
            throw new NotAuthorizedException(u.getId());
    }
}
