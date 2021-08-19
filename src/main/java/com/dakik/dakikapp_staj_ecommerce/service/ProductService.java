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
    private ProductRepository productRepo;

    @Autowired
    private ProductMapper productMapper;

    private Object response;
    private HttpStatus statusCode;

    private boolean isProductCodeUnique(int productCode) {
        return productRepo.findByProductCode(productCode).isEmpty();
    }

    private boolean isProductNameUnique(String productName) {
        return productRepo.findByProductName(productName).isEmpty();
    }

    public ResponseEntity<Object> getProductList() {
        List<ProductDto> users = new ArrayList<>();
        productRepo.findActiveAll().forEach((p) -> users.add(productMapper.productToDto(p)));
        return new ResponseEntity<Object>(users, HttpStatus.OK);
    }

    public ResponseEntity<Object> getProduct(int id) {
        productRepo.findActiveById(id).ifPresentOrElse(product -> {
            response = productMapper.productToDto(product);
            statusCode = HttpStatus.OK;
        }, () -> {
            response = new ErrorResponse("Product not found");
            statusCode = HttpStatus.NOT_FOUND;
        });
        return new ResponseEntity<Object>(response, statusCode);
    }

    public ResponseEntity<Object> deleteProduct(int id) {
        productRepo.findById(id).ifPresentOrElse(product -> {
            response = productMapper.productToDto(product);
            statusCode = HttpStatus.OK;
            productRepo.setProductPassive(id);
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
        return new ResponseEntity<Object>(
                productMapper.productToDto(productRepo.save(productMapper.dtoToProduct(p).setActive())),
                HttpStatus.CREATED);
    }
}