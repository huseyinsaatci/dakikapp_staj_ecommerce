package com.dakik.dakikapp_staj_ecommerce.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AuthRequest {
    private String email;
    private String password;
}