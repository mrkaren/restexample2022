package com.example.restexample.mapper;

import com.example.restexample.dto.CreateUserDto;
import com.example.restexample.dto.UserDto;
import com.example.restexample.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper {

//    @Mapping(target = "role", defaultValue = "USER")
    User map(CreateUserDto createUserDto);

    UserDto map(User user);
}
