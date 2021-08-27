package com.dakik.dakikapp_staj_ecommerce.controller;

import com.dakik.dakikapp_staj_ecommerce.dto.AuthRequest;
import com.dakik.dakikapp_staj_ecommerce.dto.LoginResponse;
import com.dakik.dakikapp_staj_ecommerce.model.User;
import com.dakik.dakikapp_staj_ecommerce.repository.UserRepository;
import com.dakik.dakikapp_staj_ecommerce.security.jwt.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository rep;

    @PostMapping("/login")
    public LoginResponse createToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorrect username or password", ex);
        }
        final User user = rep.findByEmail(authRequest.getEmail()).get();
        final String jwt = jwtUtil.generateToken(user.getEmail());
        LoginResponse loginResponse = new LoginResponse(jwt, user.getId());

        return loginResponse;
    }
}