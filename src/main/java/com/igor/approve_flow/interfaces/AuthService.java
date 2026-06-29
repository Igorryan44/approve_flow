package com.igor.approve_flow.interfaces;

import com.igor.approve_flow.dtos.request.LoginRequestDto;
import com.igor.approve_flow.dtos.request.RegisterUserRequestDto;
import com.igor.approve_flow.dtos.response.LoginResponseDto;
import com.igor.approve_flow.dtos.response.RegisterUserResponseDto;

public interface AuthService {
    RegisterUserResponseDto register(RegisterUserRequestDto request);
    LoginResponseDto login(LoginRequestDto request);
}
