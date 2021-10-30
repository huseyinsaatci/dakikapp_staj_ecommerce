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
@Entity
@Table(name = "cart_item", schema = "public")
@Data
public class CartItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "cart_id")
    private int cartId;

    @Column(name = "product_id")
    private int productId;

    private int quantity;
}