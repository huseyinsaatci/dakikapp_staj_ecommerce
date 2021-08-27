package com.dakik.dakikapp_staj_ecommerce.mapper;

import com.dakik.dakikapp_staj_ecommerce.dto.UserDto;
import com.dakik.dakikapp_staj_ecommerce.model.User;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public UserDto userToDto(User u);

    public User dtoToUser(UserDto u);
}