package com.dakik.dakikapp_staj_ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import com.dakik.dakikapp_staj_ecommerce.dto.UserDto;
import com.dakik.dakikapp_staj_ecommerce.mapper.UserMapper;
import com.dakik.dakikapp_staj_ecommerce.model.Cart;
import com.dakik.dakikapp_staj_ecommerce.model.User;
import com.dakik.dakikapp_staj_ecommerce.repository.CartRepository;
import com.dakik.dakikapp_staj_ecommerce.repository.UserRepository;
import com.dakik.dakikapp_staj_ecommerce.util.ErrorResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    private Object response;
    private HttpStatus statusCode;

    private boolean isEmailUnique(String email) {
        return userRepo.findByEmail(email).isEmpty();
    }

    private boolean isFullNameUnique(String firstName, String lastName) {
        return userRepo.findByFullName(firstName, lastName).isEmpty();
    }

    private boolean isPhoneNumberUnique(String phoneNumber) {
        return userRepo.findByPhoneNumber(phoneNumber).isEmpty();
    }

    public ResponseEntity<Object> getAllUsers() {
        List<UserDto> users = new ArrayList<>();
        userRepo.findActiveAll().forEach((u) -> users.add(userMapper.userToDto(u)));
        return new ResponseEntity<Object>(users, HttpStatus.OK);
    }

    public ResponseEntity<Object> getUser(int id) {
        userRepo.findById(id).ifPresentOrElse(user -> {
            response = userMapper.userToDto(user);
            statusCode = HttpStatus.OK;
        }, () -> {
            response = new ErrorResponse("User not found");
            statusCode = HttpStatus.NOT_FOUND;
        });
        return new ResponseEntity<Object>(response, statusCode);
    }

    public ResponseEntity<Object> deleteUser(int id) {
        userRepo.findById(id).ifPresentOrElse(user -> {
            response = userMapper.userToDto(user);
            statusCode = HttpStatus.OK;
            userRepo.setUserPassive(id);
            cartRepo.setCartPassiveWithUserId(id);
        }, () -> {
            response = new ErrorResponse("User not found");
            statusCode = HttpStatus.NOT_FOUND;
        });
        return new ResponseEntity<Object>(response, statusCode);
    }

    public ResponseEntity<Object> addUser(UserDto u) {
        ErrorResponse error = new ErrorResponse("Duplicate user");
        if (!isEmailUnique(u.getEmail())) {
            error.addSubError("User already exist with email: " + u.getEmail());
        }
        if (!isFullNameUnique(u.getFirstName(), u.getLastName())) {
            error.addSubError("User already exist with name: " + u.getFirstName() + " " + u.getLastName());
        }
        if (!isPhoneNumberUnique(u.getPhoneNumber())) {
            error.addSubError("User already exist with phone number: " + u.getPhoneNumber());
        }
        if (error.hasError())
            return new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        User createdUser = userRepo.save(userMapper.dtoToUser(u).setActive());
        cartRepo.save(new Cart(createdUser.getId()));
        return new ResponseEntity<Object>(userMapper.userToDto(createdUser), HttpStatus.CREATED);
    }
}