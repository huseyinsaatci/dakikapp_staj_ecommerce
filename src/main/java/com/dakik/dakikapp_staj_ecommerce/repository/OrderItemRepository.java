package com.dakik.dakikapp_staj_ecommerce.repository;

import java.util.List;

import com.dakik.dakikapp_staj_ecommerce.dto.ProductDto;
import com.dakik.dakikapp_staj_ecommerce.model.OrderItem;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {
    @Query(value = "SELECT NEW com.dakik.dakikapp_staj_ecommerce.dto.ProductDto(p.id, p.productCode, p.productName, p.imageUrl, i.quantity) FROM Product p JOIN OrderItem i ON p.id = i.productId WHERE i.orderId = ?1")
    List<ProductDto> findAllProductsByOrderId(int orderId);
}