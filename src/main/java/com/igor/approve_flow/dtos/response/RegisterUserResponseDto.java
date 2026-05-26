package com.igor.approve_flow.dtos.response;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record RegisterUserResponseDto(
        Long id,
        String name,
        String email,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime created_at
) {
}
