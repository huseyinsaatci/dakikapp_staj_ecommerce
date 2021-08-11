package com.dakik.dakikapp_staj_ecommerce.repository;

import java.util.Optional;

import com.dakik.dakikapp_staj_ecommerce.model.Product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Query(value = "FROM Product p WHERE p.productCode = ?1 AND p.stockQuantity > 0")
    Optional<Product> findByProductCodeIfInStock(int productCode);
    // product code x and qty >0

    Optional<Product> findByProductCode(int productCode);

    Optional<Product> findByProductName(String productName);

}