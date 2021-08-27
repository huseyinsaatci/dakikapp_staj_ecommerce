package com.dakik.dakikapp_staj_ecommerce.mapper;

import java.util.List;

import com.dakik.dakikapp_staj_ecommerce.dto.ProductDto;
import com.dakik.dakikapp_staj_ecommerce.model.OrderItem;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    public List<OrderItem> productsToOrderItems(List<ProductDto> items);
}