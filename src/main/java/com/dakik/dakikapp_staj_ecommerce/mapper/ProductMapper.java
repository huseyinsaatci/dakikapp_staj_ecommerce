package com.dakik.dakikapp_staj_ecommerce.mapper;

import com.dakik.dakikapp_staj_ecommerce.dto.ProductDto;
import com.dakik.dakikapp_staj_ecommerce.model.Product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "id", target = "productId")
    public ProductDto productToDto(Product p);

    @Mapping(target = "id", source = "productId")
    public Product dtoToProduct(ProductDto p);

}