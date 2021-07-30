package com.dakik.dakikapp_staj_ecommerce;

public class ProductNotFoundException extends RuntimeException {
    ProductNotFoundException(int id) {
        super("Could not find product " + id);
    }
}
