package com.dakik.dakikapp_staj_ecommerce.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.dakik.dakikapp_staj_ecommerce.dto.AddItemRequest;
import com.dakik.dakikapp_staj_ecommerce.dto.DeleteItemRequest;
import com.dakik.dakikapp_staj_ecommerce.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RequestMapping("/carts")
@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getCart(@PathVariable int userId) {
        return cartService.getCartWithItems(userId);
    }

    @PostMapping()
    public ResponseEntity<Object> addItemToCart(@Valid @RequestBody AddItemRequest item) {
        return cartService.addItem(item);
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteItemFromCart(@RequestBody DeleteItemRequest item) {
        return cartService.deleteItemFromCart(item);
    }
}