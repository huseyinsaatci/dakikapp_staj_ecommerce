package com.dakik.dakikapp_staj_ecommerce;

public class ProductAlreadyExistException extends RuntimeException {
    ProductAlreadyExistException(int id) {
        super("Product already exists:" + id);

    }
}
