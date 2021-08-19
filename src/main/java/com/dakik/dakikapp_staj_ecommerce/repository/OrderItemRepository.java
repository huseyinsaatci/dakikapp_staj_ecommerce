package com.dakik.dakikapp_staj_ecommerce.repository;

import com.dakik.dakikapp_staj_ecommerce.model.OrderItem;

import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {

}