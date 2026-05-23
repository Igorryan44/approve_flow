package com.igor.approve_flow.dtos.request;

import jakarta.validation.constraints.NotNull;

public record LoginRequestDto(
        @NotNull
        String email,
        @NotNull
        String password
) {
}
