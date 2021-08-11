package com.dakik.dakikapp_staj_ecommerce.service;

import com.dakik.dakikapp_staj_ecommerce.model.Product;
import com.dakik.dakikapp_staj_ecommerce.repository.ProductRepository;
import com.dakik.dakikapp_staj_ecommerce.util.ErrorResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository rep;

    private Object response;
    private HttpStatus statusCode;

    private boolean isProductCodeUnique(int productCode) {
        return rep.findByProductCode(productCode).isEmpty();
    }

    private boolean isProductNameUnique(String productName) {
        return rep.findByProductName(productName).isEmpty();
    }

    public ResponseEntity<Object> getProductList() {
        return new ResponseEntity<Object>(rep.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Object> getProduct(int id) {
        rep.findById(id).ifPresentOrElse(product -> {
            response = product;
            statusCode = HttpStatus.OK;
        }, () -> {
            response = new ErrorResponse("Product not found");
            statusCode = HttpStatus.NOT_FOUND;
        });
        return new ResponseEntity<Object>(response, statusCode);
    }

    public ResponseEntity<Object> deleteProduct(int id) {
        rep.findById(id).ifPresentOrElse(product -> {
            response = product;
            statusCode = HttpStatus.OK;
            rep.deleteById(id);
        }, () -> {
            response = new ErrorResponse("Product not found");
            statusCode = HttpStatus.NOT_FOUND;
        });
        return new ResponseEntity<Object>(response, statusCode);
    }

    public ResponseEntity<Object> addProduct(Product p) {
        ErrorResponse error = new ErrorResponse("Duplicate product");
        boolean errorExist = false;
        if (!isProductCodeUnique(p.getProductCode())) {
            error.addSubError("Product code already exist: " + p.getProductCode());
            errorExist = true;
        }
        if (!isProductNameUnique(p.getProductName())) {
            error.addSubError("Product name already exist: " + p.getProductName());
            errorExist = true;
        }
        if (errorExist)
            return new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
        return new ResponseEntity<Object>(rep.save(p), HttpStatus.CREATED);
    }
}