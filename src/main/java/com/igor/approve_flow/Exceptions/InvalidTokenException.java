package com.igor.approve_flow.Exceptions;

public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException() {
        super("Invalid authentication token");
    }

    public InvalidTokenException(String message) {
        super(message);
    }
}
