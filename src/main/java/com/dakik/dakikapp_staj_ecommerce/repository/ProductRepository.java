package com.dakik.dakikapp_staj_ecommerce.repository;

import java.util.Optional;

import com.dakik.dakikapp_staj_ecommerce.model.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    Optional<Product> findByProductcode(int product_code);
}
