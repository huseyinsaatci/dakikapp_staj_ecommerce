package com.dakik.dakikapp_staj_ecommerce;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
    protected NotFoundException throwProductNotFoundException(int id) {
        return new NotFoundException("Could not find product, ID:" + id);
    }
}