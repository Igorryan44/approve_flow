package com.igor.approve_flow.dtos.request;

public record UpdatePasswordDto (
        String password,
        String new_password
){
}
