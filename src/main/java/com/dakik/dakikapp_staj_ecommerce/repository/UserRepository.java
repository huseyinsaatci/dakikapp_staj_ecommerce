package com.dakik.dakikapp_staj_ecommerce.repository;

import java.util.Optional;

import com.dakik.dakikapp_staj_ecommerce.model.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    Optional<User> findByFirstName(String firstName);

    Optional<User> findByLastName(String lastName);

    @Query(value = "FROM User u WHERE u.firstName =?1 AND u.lastName =?2")
    Optional<User> findByFullName(String firstName, String lastName);

    Optional<User> findByPhoneNumber(String phoneNumber);
}