package com.igor.approve_flow.mapper;

import com.igor.approve_flow.dtos.request.UserRequestDto;
import com.igor.approve_flow.dtos.response.RegisterUserResponse;
import com.igor.approve_flow.dtos.response.UserResponseDto;
import com.igor.approve_flow.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toDto(User user);
    RegisterUserResponse toRegisterDto(User user);

    List<UserResponseDto> toDto (List<User> users);

    User toEntity(UserRequestDto userRequest);

}
