package com.dakik.dakikapp_staj_ecommerce.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DeleteItemRequest {
    @NotNull(message = "'user_id' cannot be null")
    private int userId;

    @NotNull(message = "'product_id' cannot be null")
    private int productId;
}