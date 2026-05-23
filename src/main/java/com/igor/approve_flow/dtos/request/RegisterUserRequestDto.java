package com.igor.approve_flow.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record RegisterUserRequestDto(
        @NotNull
        String name,
        @NotNull
        @Email
        String email,
        @NotNull
        String password
) {
}
