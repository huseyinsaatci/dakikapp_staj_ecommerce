package com.dakik.dakikapp_staj_ecommerce.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

import com.dakik.dakikapp_staj_ecommerce.dto.ProductDto;
import com.dakik.dakikapp_staj_ecommerce.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RequestMapping("/products")
@CrossOrigin(origins = "*")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<Object> getProductList() {
        return productService.getProductList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable int id) {
        return productService.getProduct(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable int id, WebRequest request) {
        return productService.deleteProduct(id);
    }

    @PostMapping()
    public ResponseEntity<Object> addProduct(@Valid @RequestBody ProductDto p, WebRequest request) {
        return productService.addProduct(p);
    }
}