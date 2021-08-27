package com.dakik.dakikapp_staj_ecommerce.mapper;

import com.dakik.dakikapp_staj_ecommerce.dto.AddItemRequest;
import com.dakik.dakikapp_staj_ecommerce.dto.ProductDto;
import com.dakik.dakikapp_staj_ecommerce.model.CartItem;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    public CartItem requestToCartItem(AddItemRequest c);

    public ProductDto cartItemToProduct(CartItem i);
}