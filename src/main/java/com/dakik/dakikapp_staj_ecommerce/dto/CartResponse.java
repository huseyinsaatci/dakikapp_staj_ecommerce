package com.dakik.dakikapp_staj_ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CartResponse {
    private int userId, total;
    List<CartItemResponse> items = new ArrayList<>();

    public void addItem(CartItemResponse item) {
        items.add(item);
    }
}