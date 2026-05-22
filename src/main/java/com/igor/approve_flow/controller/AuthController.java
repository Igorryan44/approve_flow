package com.igor.approve_flow.controller;

import com.igor.approve_flow.dtos.request.LoginRequestDto;
import com.igor.approve_flow.dtos.request.RegisterUserRequestDto;
import com.igor.approve_flow.dtos.response.LoginResponseDto;
import com.igor.approve_flow.dtos.response.RegisterUserResponseDto;
import com.igor.approve_flow.service.UserService;
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

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDto> register(@Valid @RequestBody RegisterUserRequestDto registerUserRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(registerUserRequestDto));
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(userService.login(loginRequestDto));
    }


}
