package com.dakik.dakikapp_staj_ecommerce.repository;

import java.util.List;
import java.util.Optional;

import com.dakik.dakikapp_staj_ecommerce.dto.ProductDto;
import com.dakik.dakikapp_staj_ecommerce.model.CartItem;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CartItemRepository extends CrudRepository<CartItem, Integer> {
    Optional<CartItem> findByCartIdAndProductId(int cartId, int productId);

    @Query(value = "SELECT NEW com.dakik.dakikapp_staj_ecommerce.dto.ProductDto(p.id, p.productCode, p.productName, p.imageUrl, i.quantity) FROM Product p JOIN CartItem i ON p.id = i.productId WHERE i.cartId = ?1")
    List<ProductDto> findAllProductsByCartId(int cartId);

    @Transactional
    void deleteAllByCartId(int cartId);

    @Transactional
    @Query("DELETE CartItem WHERE cartId=?1 AND productId=?2")
    int deleteByCartIdAndProductId(int cartId, int productId);

    @Transactional
    @Modifying
    @Query("UPDATE CartItem SET quantity =?3 WHERE cartId=?1 AND productId=?2")
    void updateItemQuantity(int cartId, int productId, int quantity);
}