package com.dakik.dakikapp_staj_ecommerce.mapper;

import com.dakik.dakikapp_staj_ecommerce.dto.CartItemResponse;
import com.dakik.dakikapp_staj_ecommerce.dto.CartRequest;
import com.dakik.dakikapp_staj_ecommerce.model.CartItem;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);

    public CartItemResponse cartItemToResponse(CartItem c);

    public CartItem requestToItem(CartRequest c);

}