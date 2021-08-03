package com.dakik.dakikapp_staj_ecommerce;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RequestMapping("/users")
@CrossOrigin(origins = "*")
@RestController
public class UserController {
    @Autowired
    private UserRepository rep;

    @Autowired
    private UserService userService;

    @GetMapping()
    public Iterable<User> getAllUsers() {
        return rep.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return rep.findById(id).orElseThrow(() -> userService.throwProductNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id, WebRequest request) {
        rep.findById(id).orElseThrow(() -> userService.throwProductNotFoundException(id));
        rep.deleteById(id);
        return GeneralExceptionHandler.getSuccessfulResponseEntity(request);
    }

    @PostMapping()
    public User addUser(@Valid @RequestBody User u) {
        return rep.save(u);
    }

    @GetMapping("/login")
    public ResponseEntity<Object> logIn(@RequestParam String email, @RequestParam String password, WebRequest request) {
        return userService.validateUser(
                rep.findByEmail(email).orElseThrow(() -> userService.throwProductNotFoundException(email)), email,
                password, request);
    }
}
