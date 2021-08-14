package com.dakik.dakikapp_staj_ecommerce.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

import org.springframework.stereotype.Component;

@Data
@Component
public class UserDto {
    @NotBlank(message = "'first_name' cannot be blank")
    private String firstName;

    @NotBlank(message = "'last_name'cannot be blank")
    private String lastName;

    @NotBlank(message = "'email' cannot be blank")
    private String email;

    @NotBlank(message = "'phone_number' cannot be blank")
    private String phoneNumber;

    @NotBlank(message = "'password' cannot be blank")
    private String password;
}