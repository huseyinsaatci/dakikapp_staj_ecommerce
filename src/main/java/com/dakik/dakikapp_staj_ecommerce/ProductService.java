package com.dakik.dakikapp_staj_ecommerce;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
    public boolean checkIfProductAlreadyExist(Product p, ProductRepository rep) {
        return (rep.findByProductcode(p.getProductCode())
                .orElseThrow(() -> new ProductAlreadyExistException(p.getProductCode()))) != null;
    }
}