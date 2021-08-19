package com.dakik.dakikapp_staj_ecommerce.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.dakik.dakikapp_staj_ecommerce.dto.CartRequest;
import com.dakik.dakikapp_staj_ecommerce.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RequestMapping("/carts")
@CrossOrigin(origins = "*")
@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCart(@PathVariable int id) {
        return cartService.getCartWithItems(id);
    }

    @PostMapping()
    public ResponseEntity<Object> addItemToCart(@Valid @RequestBody CartRequest item) {
        return cartService.addItem(item);
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteItemFromCart(@RequestBody CartRequest item) {
        return cartService.deleteItemFromCart(item);
    }
}