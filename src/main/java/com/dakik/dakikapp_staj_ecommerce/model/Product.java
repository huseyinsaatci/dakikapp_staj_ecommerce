package com.dakik.dakikapp_staj_ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "product", schema = "dakikapp")
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "product_code")
    @NotNull(message = "'productcode' cannot be null")
    private int productCode;

    @Column(name = "product_name")
    @NotEmpty(message = "'productname' cannot be empty")
    private String productName;

    @Column(name = "image_url")
    private String imageUrl;

    // Stockquantity stock > 0
    @PositiveOrZero
    @NotNull
    @Column(name = "stock_quantity")
    private int stockQuantity;
}