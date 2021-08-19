package com.dakik.dakikapp_staj_ecommerce.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class CartRequest {
    @NotNull(message = "'cart_id' cannot be null")
    private int cartId;

    @NotNull(message = "'product_id' cannot be null")
    private int productId;

    @Positive(message = "'quantity' must be bigger than zero")
    private int quantity;
}
