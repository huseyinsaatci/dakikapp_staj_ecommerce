package com.dakik.dakikapp_staj_ecommerce.repository;

import java.util.Optional;

import com.dakik.dakikapp_staj_ecommerce.model.CartItem;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CartItemRepository extends CrudRepository<CartItem, Integer> {
    Optional<CartItem> findByCartId(int cartId);

    Iterable<CartItem> findAllByCartId(int cartId);

    Optional<CartItem> findByCartIdAndProductId(int cartId, int productId);

    @Transactional
    @Modifying
    @Query("DELETE CartItem WHERE cartId=?1 AND productId=?2")
    void deleteByCartIdAndProductId(int cartId, int productId);

    @Transactional
    @Modifying
    @Query("UPDATE CartItem SET quantity =?3 WHERE cartId=?1 AND productId=?2")
    void updateItemQuantity(int cartId, int productId, int quantity);
}