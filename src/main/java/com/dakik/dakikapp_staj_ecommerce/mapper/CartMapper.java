package com.dakik.dakikapp_staj_ecommerce.mapper;

import com.dakik.dakikapp_staj_ecommerce.dto.CartResponse;
import com.dakik.dakikapp_staj_ecommerce.model.Cart;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    public CartResponse cartToResponse(Cart c);
}