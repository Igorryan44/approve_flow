package com.igor.approve_flow.Exceptions;

public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException() {
        super("Passwords don't match, try another password");
    }

    public IncorrectPasswordException(String message) {

        super(message);
    }
}