package com.dakik.dakikapp_staj_ecommerce.service;

import java.util.List;

import com.dakik.dakikapp_staj_ecommerce.dto.ProductDto;
import com.dakik.dakikapp_staj_ecommerce.dto.AddItemRequest;
import com.dakik.dakikapp_staj_ecommerce.dto.DeleteItemRequest;
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
    private CartMapper cartMapper;

    @Autowired
    private ProductRepository productRepo;

    private Object response;
    private HttpStatus statusCode;

    private void modifyQuantity(int cartId, int productId, int quantity) {
        cartItemRepo.updateItemQuantity(cartId, productId, quantity);
    }

    private boolean productExist(int productId) {
        return productRepo.findById(productId).isPresent();
    }

    private boolean itemAlreadyExistInCart(int cartId, int productId) {
        return cartItemRepo.findByCartIdAndProductId(cartId, productId).isPresent();
    }

    private List<ProductDto> getAllCartItems(int cartId) {
        return cartItemRepo.findAllProductsByCartId(cartId);
    }

    public ResponseEntity<Object> getCartWithItems(int userId) {
        cartRepo.findByUserId(userId).ifPresentOrElse(cart -> {
            List<ProductDto> items = getAllCartItems(cart.getId());
            response = items;
            statusCode = HttpStatus.OK;
        }, () -> {
            System.out.println(userId);
            response = new ErrorResponse("User Not Found");
            statusCode = HttpStatus.NOT_FOUND;
        });
        return new ResponseEntity<Object>(response, statusCode);
    }

    public ResponseEntity<Object> deleteItemFromCart(DeleteItemRequest request) {
        ErrorResponse error = new ErrorResponse("Item could not be deleted");
        cartRepo.findByUserId(request.getUserId()).ifPresentOrElse(cart -> {
            int deletedItem = cartItemRepo.deleteByCartIdAndProductId(cart.getId(), request.getProductId());
            if (deletedItem == 0) {
                error.addSubError("Product not found with id: " + request.getProductId());
            } else {
                response = null;
            }
        }, () -> {
            error.addSubError("User not found with id: " + request.getUserId());
        });
        if (error.hasError()) {
            return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(response, statusCode);
    }

    public ResponseEntity<Object> addItem(AddItemRequest item) {
        ErrorResponse error = new ErrorResponse("Item could not be added");
        CartItem cartItem = cartMapper.requestToCartItem(item);
        cartRepo.findByUserId(item.getUserId()).ifPresentOrElse(cart -> {
            if (itemAlreadyExistInCart(cart.getId(), item.getProductId())) {
                modifyQuantity(cart.getId(), item.getProductId(), item.getQuantity());
            } else {
                cartItem.setCartId(cart.getId());
                cartItemRepo.save(cartItem);
            }
        }, () -> {
            error.addSubError("User not found with id: " + item.getUserId());
        });
        if (!productExist(item.getProductId())) {
            error.addSubError("Product does not exist with id: " + item.getProductId());
        }
        if (error.hasError()) {
            return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Object>(cartMapper.cartItemToProduct(cartItem), HttpStatus.OK);
    }
}