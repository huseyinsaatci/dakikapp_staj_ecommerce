package com.dakik.dakikapp_staj_ecommerce;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
    @Autowired
    private UserRepository rep;

    @Autowired
    private UserService userService;

    @GetMapping("/user/all")
    public Iterable<User> getAllUsers() {
        return rep.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable int id) {
        return rep.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    public User deleteUser(@PathVariable int id) {
        User u = rep.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        rep.delete(u);
        return u;
    }

    @PostMapping("/user")
    public User addUser(@Valid @RequestBody User u) {
        rep.findByEmail(u.getEmail()).ifPresent(s -> {
            throw new UserAlreadyExistException(u.getId());
        });
        return rep.save(u);
    }

    @GetMapping("/login")
    public void logIn(@RequestParam String email, @RequestParam String password) {
        userService.validateUser(rep.findByEmail(email).get(), password);
    }
}
