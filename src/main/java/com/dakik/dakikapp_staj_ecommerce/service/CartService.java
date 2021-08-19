package com.dakik.dakikapp_staj_ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import com.dakik.dakikapp_staj_ecommerce.dto.CartItemResponse;
import com.dakik.dakikapp_staj_ecommerce.dto.CartRequest;
import com.dakik.dakikapp_staj_ecommerce.dto.CartResponse;
import com.dakik.dakikapp_staj_ecommerce.mapper.CartItemMapper;
import com.dakik.dakikapp_staj_ecommerce.mapper.CartMapper;
import com.dakik.dakikapp_staj_ecommerce.model.CartItem;
import com.dakik.dakikapp_staj_ecommerce.repository.CartItemRepository;
import com.dakik.dakikapp_staj_ecommerce.repository.CartRepository;
import com.dakik.dakikapp_staj_ecommerce.repository.ProductRepository;
import com.dakik.dakikapp_staj_ecommerce.util.ErrorResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartItemRepository cartItemRepo;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductRepository productRepository;

    private Object response;
    private HttpStatus statusCode;

    private void modifyQuantity(CartRequest item) {
        cartItemRepo.updateItemQuantity(item.getCartId(), item.getProductId(), item.getQuantity());
    }

    private boolean productExist(int productId) {
        return productRepository.findById(productId).isPresent();
    }

    private boolean cartExist(int cartId) {
        return cartRepo.findById(cartId).isPresent();
    }

    private boolean itemAlreadyExistInCart(int cartId, int productId) {
        return cartItemRepo.findByCartIdAndProductId(cartId, productId).isPresent();
    }

    private List<CartItemResponse> getAllCartItems(int cartId) {
        List<CartItemResponse> response = new ArrayList<>();
        cartItemRepo.findAllByCartId(cartId).forEach((item) -> {
            response.add(cartItemMapper.cartItemToResponse(item));
        });
        return response;
    }

    public ResponseEntity<Object> getCartWithItems(int cartId) {
        cartRepo.findById(cartId).ifPresentOrElse(cart -> {
            CartResponse cartResponse = cartMapper.cartToResponse(cart);
            cartResponse.setItems(getAllCartItems(cartId));
            response = cartResponse;
            statusCode = HttpStatus.OK;
        }, () -> {
            response = new ErrorResponse("There is no such cart");
            statusCode = HttpStatus.NOT_FOUND;
        });
        return new ResponseEntity<Object>(response, statusCode);
    }

    public ResponseEntity<Object> deleteItemFromCart(CartRequest requestItem) {
        cartItemRepo.findByCartIdAndProductId(requestItem.getCartId(), requestItem.getProductId())
                .ifPresentOrElse(item -> {
                    response = cartItemMapper.cartItemToResponse(item);
                    statusCode = HttpStatus.OK;
                    cartItemRepo.deleteByCartIdAndProductId(requestItem.getCartId(), requestItem.getProductId());
                }, () -> {
                    response = new ErrorResponse("Item not found");
                    statusCode = HttpStatus.NOT_FOUND;
                });
        return new ResponseEntity<Object>(response, statusCode);
    }

    public ResponseEntity<Object> addItem(CartRequest item) {
        ErrorResponse error = new ErrorResponse("Item could not be added");
        CartItem cartItem = cartItemMapper.requestToItem(item);
        if (!cartExist(item.getCartId())) {
            error.addSubError("Cart not found with cart id: " + item.getCartId());
        } else if (itemAlreadyExistInCart(item.getCartId(), item.getProductId())) {
            modifyQuantity(item);
        } else if (!productExist(item.getProductId())) {
            error.addSubError("Product does not exist with id: " + item.getProductId());
        } else {
            cartItemRepo.save(cartItem);
        }
        if (error.hasError()) {
            return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
        }
        // cartRepo.modifyTotal(item.getCartId(), 0);
        return new ResponseEntity<Object>(cartItemMapper.cartItemToResponse(cartItem), HttpStatus.OK);
    }

}