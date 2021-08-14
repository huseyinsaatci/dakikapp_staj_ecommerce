package com.dakik.dakikapp_staj_ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "user", schema = "dakikapp")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "first_name")
    @NotBlank(message = "'first_name' cannot be blank")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "'last_name'cannot be blank")
    private String lastName;

    @NotBlank(message = "'email' cannot be blank")
    private String email;

    @Column(name = "phone_number")
    @NotBlank(message = "'phone_number' cannot be blank")
    private String phoneNumber;

    @NotBlank(message = "'password' cannot be blank")
    private String password;
}