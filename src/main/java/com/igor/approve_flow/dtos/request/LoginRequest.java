package com.igor.approve_flow.dtos.request;

public record LoginRequest(
        String email,
        String password
) {
}
