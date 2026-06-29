package com.igor.approve_flow.controller;

import com.igor.approve_flow.dtos.request.LoginRequestDto;
import com.igor.approve_flow.dtos.request.RegisterUserRequestDto;
import com.igor.approve_flow.dtos.response.LoginResponseDto;
import com.igor.approve_flow.dtos.response.RegisterUserResponseDto;
import com.igor.approve_flow.interfaces.AuthService;
import com.igor.approve_flow.service.AuthServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDto> register(@Valid @RequestBody RegisterUserRequestDto registerUserRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerUserRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }
}
