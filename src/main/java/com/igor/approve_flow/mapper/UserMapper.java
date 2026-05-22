package com.igor.approve_flow.mapper;

import com.igor.approve_flow.dtos.request.UserRequestDto;
import com.igor.approve_flow.dtos.response.RegisterUserResponseDto;
import com.igor.approve_flow.dtos.response.UserResponseDto;
import com.igor.approve_flow.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toDto(User user);
    RegisterUserResponseDto toRegisterDto(User user);

    List<UserResponseDto> toDto (List<User> users);

    User toEntity(UserRequestDto userRequest);

}