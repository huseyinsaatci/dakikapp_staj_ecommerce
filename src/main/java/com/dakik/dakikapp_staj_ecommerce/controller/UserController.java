package com.dakik.dakikapp_staj_ecommerce.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.dakik.dakikapp_staj_ecommerce.dto.UserDto;
import com.dakik.dakikapp_staj_ecommerce.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<Object> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> addUser(@Valid @RequestBody UserDto u) {
        return userService.addUser(u);
    }
}