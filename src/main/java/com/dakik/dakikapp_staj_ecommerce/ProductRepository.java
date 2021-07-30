package com.dakik.dakikapp_staj_ecommerce;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    Optional<Product> findByProductcode(int product_code);
}
