package com.dakik.dakikapp_staj_ecommerce;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserRepository rep;

    @GetMapping(path = "/all")
    public Iterable<User> getAllUsers() {
        return rep.findAll();
    }

    @GetMapping(path = "/get")
    public User getUser(@RequestParam int id) {
        return rep.findById(id).get();
    }

    @DeleteMapping(path = "/delete")
    public User deleteUser(@RequestParam int id) {
        User u = rep.findById(id).get();
        rep.delete(u);
        return u;
    }

    @PostMapping(path = "/add")
    public User addUser(@RequestParam String firstname, @RequestParam String lastname, String phonenumber,
            @RequestParam String email, @RequestParam String password) {
        User u = new User(firstname, lastname, email, phonenumber, password);
        rep.save(u);
        return u;
    }

    @GetMapping(path = "/login")
    public boolean logIn(@RequestParam String email, @RequestParam String password) {
        User u = rep.findByEmail(email);
        return u != null && password.equals(u.getPassword());
    }
}
