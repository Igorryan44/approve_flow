package com.igor.approve_flow.dtos.request;

import jakarta.validation.constraints.NotNull;

public record UpdatePasswordDto (
        @NotNull
        String password,
        @NotNull
        String new_password
){
}
