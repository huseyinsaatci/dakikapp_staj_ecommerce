package com.dakik.dakikapp_staj_ecommerce.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String jwtToken;
    private int userId;

    public LoginResponse(String jwtToken, int userId) {
        this.jwtToken = jwtToken;
        this.userId = userId;
    }
}