package com.igor.approve_flow.Exceptions;

public class InvalidArgumentException extends RuntimeException {

    public InvalidArgumentException() {
        super("Email or Password is invalid");
    }

    public InvalidArgumentException(String message) {

        super(message);
    }
}