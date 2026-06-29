package com.igor.approve_flow.interfaces;

import com.igor.approve_flow.dtos.request.UpdatePasswordDto;
import com.igor.approve_flow.dtos.response.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> listAll();
    UserResponseDto findById(Long id);
    UserResponseDto findByEmail(String email);
    void deleteById(Long id);
    UpdatePasswordDto updatePassword(Long id, String password, String new_password);
}
