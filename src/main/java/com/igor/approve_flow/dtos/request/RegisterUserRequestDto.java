package com.igor.approve_flow.dtos.request;

import jakarta.validation.constraints.Email;

public record RegisterUserRequestDto(
        String name,
        @Email
        String email,
        String password
) {
}
