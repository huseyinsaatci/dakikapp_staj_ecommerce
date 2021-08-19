package com.dakik.dakikapp_staj_ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private int productCode;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    @Column(name = "is_active")
    private boolean isActive;

    public Product setActive() {
        isActive = true;
        return this;
    }
}