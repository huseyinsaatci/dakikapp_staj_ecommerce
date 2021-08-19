package com.dakik.dakikapp_staj_ecommerce.dto;

import lombok.Data;

@Data
public class CartItemResponse {
    private int productId, quantity;
}