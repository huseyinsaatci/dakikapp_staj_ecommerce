package com.dakik.dakikapp_staj_ecommerce.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import com.dakik.dakikapp_staj_ecommerce.model.User;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    Optional<User> findByFirstName(String firstName);

    Optional<User> findByLastName(String lastName);

    @Query(value = "FROM User WHERE firstName =?1 AND lastName =?2")
    Optional<User> findByFullName(String firstName, String lastName);

    Optional<User> findByPhoneNumber(String phoneNumber);

    @Query(value = "FROM User WHERE id = ?1 AND isActive=true")
    Optional<User> findById(int id);

    @Query(value = "FROM User WHERE isActive=true")
    Iterable<User> findActiveAll();

    @Transactional
    @Modifying
    @Query("UPDATE User SET isActive = false WHERE id=?1")
    void setUserPassive(int id);
}