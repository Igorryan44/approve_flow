package com.igor.approve_flow.Exceptions;

public class UserAlreadyException extends RuntimeException {

    public UserAlreadyException() {
        super("User already exists, try again with another user details!");
    }

    public UserAlreadyException(String message) {

        super(message);
    }
}