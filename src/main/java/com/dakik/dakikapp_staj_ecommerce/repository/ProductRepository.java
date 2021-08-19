package com.dakik.dakikapp_staj_ecommerce.repository;

import java.util.Optional;

import com.dakik.dakikapp_staj_ecommerce.model.Product;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Query(value = "FROM Product WHERE productCode = ?1 AND stockQuantity > 0 AND isActive=true")
    Optional<Product> findByProductCodeIfInStock(int productCode);

    @Query(value = "FROM Product WHERE productCode = ?1 AND isActive=true")
    Optional<Product> findByProductCode(int productCode);

    Optional<Product> findByProductName(String productName);

    @Query(value = "FROM Product WHERE id = ?1 AND isActive=true")
    Optional<Product> findActiveById(int id);

    @Query(value = "FROM Product WHERE isActive=true")
    Iterable<Product> findActiveAll();

    @Transactional
    @Modifying
    @Query("UPDATE Product SET isActive = false WHERE id=?1")
    void setProductPassive(int id);
}