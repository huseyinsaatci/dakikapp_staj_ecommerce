package com.dakik.dakikapp_staj_ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import com.dakik.dakikapp_staj_ecommerce.dto.ProductDto;
import com.dakik.dakikapp_staj_ecommerce.mapper.OrderMapper;
import com.dakik.dakikapp_staj_ecommerce.model.Order;
import com.dakik.dakikapp_staj_ecommerce.model.OrderItem;
import com.dakik.dakikapp_staj_ecommerce.repository.CartItemRepository;
import com.dakik.dakikapp_staj_ecommerce.repository.CartRepository;
import com.dakik.dakikapp_staj_ecommerce.repository.OrderItemRepository;
import com.dakik.dakikapp_staj_ecommerce.repository.OrderRepository;
import com.dakik.dakikapp_staj_ecommerce.repository.UserRepository;
import com.dakik.dakikapp_staj_ecommerce.util.ErrorResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderItemRepository orderItemRepo;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartItemRepository cartItemRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private OrderMapper orderMapper;

    private Object response;
    private HttpStatus statusCode;

    private List<ProductDto> getAllOrderItems(int orderId) {
        return orderItemRepo.findAllProductsByOrderId(orderId);
    }

    public ResponseEntity<Object> orderUserCart(int userId) {
        ErrorResponse error = new ErrorResponse("Ordering failed");
        cartRepo.findByUserId(userId).ifPresentOrElse(cart -> {
            Order order = orderRepo.save(new Order(userId));
            List<ProductDto> items = cartItemRepo.findAllProductsByCartId(cart.getId());
            List<OrderItem> orderItemList = orderMapper.productsToOrderItems(items);
            orderItemList.forEach(orderItem -> {
                orderItem.setOrderId(order.getId());
            });
            cartItemRepo.deleteAllByCartId(cart.getId());
            orderItemRepo.saveAll(orderItemList);
            response = items;
            statusCode = HttpStatus.CREATED;
        }, () -> {
            error.addSubError("User not found");
            response = error;
            statusCode = HttpStatus.NOT_FOUND;
        });
        return new ResponseEntity<Object>(response, statusCode);
    }

    public ResponseEntity<Object> getOrder(int userId) {
        ErrorResponse error = new ErrorResponse("Order not found");
        userRepo.findById(userId).ifPresentOrElse(user -> {
            List<List<ProductDto>> orderList = new ArrayList<>();
            orderRepo.findAllByUserId(userId).forEach(order -> {
                orderList.add(getAllOrderItems(order.getId()));
            });
            response = orderList;
            statusCode = HttpStatus.OK;
        }, () -> {
            error.addSubError("User not found with id: " + userId);
            response = error;
            statusCode = HttpStatus.NOT_FOUND;
        });
        return new ResponseEntity<Object>(response, statusCode);
    }

    public ResponseEntity<Object> deleteAllOrders(int userId) {
        ErrorResponse error = new ErrorResponse("Order could not deleted");
        userRepo.findById(userId).ifPresentOrElse(user -> {
            orderItemRepo.deleteAll();
            orderRepo.deleteAll();
            response = null;
            statusCode = HttpStatus.OK;
        }, () -> {
            error.addSubError("User not found with id: " + userId);
            response = error;
            statusCode = HttpStatus.NOT_FOUND;
        });
        return new ResponseEntity<Object>(response, statusCode);
    }
}