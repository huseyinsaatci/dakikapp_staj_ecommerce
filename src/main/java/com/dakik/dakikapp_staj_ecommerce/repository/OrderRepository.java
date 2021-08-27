package com.dakik.dakikapp_staj_ecommerce.repository;

import java.util.List;

import com.dakik.dakikapp_staj_ecommerce.model.Order;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    List<Order> findAllByUserId(int userId);
}