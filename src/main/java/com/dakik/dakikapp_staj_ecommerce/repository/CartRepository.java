package com.dakik.dakikapp_staj_ecommerce.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import com.dakik.dakikapp_staj_ecommerce.model.Cart;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Integer> {
    Optional<Cart> findByUserId(int id);

    @Transactional
    @Modifying
    @Query("UPDATE Cart SET total =?2 WHERE cartId = ?1")
    void modifyTotal(int cartId, int total);

    @Transactional
    @Modifying
    @Query("UPDATE Cart SET isActive = false WHERE UserId=?1")
    void setCartPassiveWithUserId(int userId);
}