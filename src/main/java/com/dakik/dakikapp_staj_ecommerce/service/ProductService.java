package com.dakik.dakikapp_staj_ecommerce.service;

import com.dakik.dakikapp_staj_ecommerce.exception.NotFoundException;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
    public NotFoundException throwProductNotFoundException(int id) {
        return new NotFoundException("Could not find product, ID:" + id);
    }
}