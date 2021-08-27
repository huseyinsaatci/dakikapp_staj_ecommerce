package com.dakik.dakikapp_staj_ecommerce.controller;

import com.dakik.dakikapp_staj_ecommerce.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/orders")
@RestController()
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getOrder(@PathVariable int userId) {
        return orderService.getOrder(userId);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Object> orderUserCart(@PathVariable int userId) {
        return orderService.orderUserCart(userId);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteAllOrders(@PathVariable int userId) {
        return orderService.deleteAllOrders(userId);
    }
}