package com.dakik.dakikapp_staj_ecommerce.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;

import org.springframework.stereotype.Component;

@Component
@Data
public class ProductDto {
    public ProductDto() {
    }

    public ProductDto(int productId, int productCode, String productName, String imageUrl, int quantity) {
        this.productId = productId;
        this.productCode = productCode;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
    }

    @NotNull(message = "'product_id' cannot be null")
    private int productId;

    @NotNull(message = "'product_code' cannot be null")
    private int productCode;

    @NotEmpty(message = "'product_name' cannot be empty")
    private String productName;

    private String imageUrl;

    @PositiveOrZero(message = "'stock_quantity' must be bigger than zero")
    private int quantity;
}