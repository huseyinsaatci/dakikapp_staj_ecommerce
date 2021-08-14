package com.dakik.dakikapp_staj_ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import com.dakik.dakikapp_staj_ecommerce.dto.ProductDto;
import com.dakik.dakikapp_staj_ecommerce.mapper.ProductMapper;
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

    @Autowired
    private ProductMapper mapper;

    private Object response;
    private HttpStatus statusCode;

    private boolean isProductCodeUnique(int productCode) {
        return rep.findByProductCode(productCode).isEmpty();
    }

    private boolean isProductNameUnique(String productName) {
        return rep.findByProductName(productName).isEmpty();
    }

    public ResponseEntity<Object> getProductList() {
        List<ProductDto> users = new ArrayList<>();
        rep.findAll().forEach((p) -> users.add(mapper.productToDto(p)));
        return new ResponseEntity<Object>(users, HttpStatus.OK);
    }

    public ResponseEntity<Object> getProduct(int id) {
        rep.findById(id).ifPresentOrElse(product -> {
            response = mapper.productToDto(product);
            statusCode = HttpStatus.OK;
        }, () -> {
            response = new ErrorResponse("Product not found");
            statusCode = HttpStatus.NOT_FOUND;
        });
        return new ResponseEntity<Object>(response, statusCode);
    }

    public ResponseEntity<Object> deleteProduct(int id) {
        rep.findById(id).ifPresentOrElse(product -> {
            response = mapper.productToDto(product);
            statusCode = HttpStatus.OK;
            rep.deleteById(id);
        }, () -> {
            response = new ErrorResponse("Product not found");
            statusCode = HttpStatus.NOT_FOUND;
        });
        return new ResponseEntity<Object>(response, statusCode);
    }

    public ResponseEntity<Object> addProduct(ProductDto p) {
        ErrorResponse error = new ErrorResponse("Duplicate product");
        if (!isProductCodeUnique(p.getProductCode())) {
            error.addSubError("Product code already exist: " + p.getProductCode());
        }
        if (!isProductNameUnique(p.getProductName())) {
            error.addSubError("Product name already exist: " + p.getProductName());
        }
        if (error.hasError())
            return new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
        return new ResponseEntity<Object>(mapper.productToDto(rep.save(mapper.dtoToProduct(p))), HttpStatus.CREATED);
    }
}