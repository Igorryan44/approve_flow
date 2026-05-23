package com.igor.approve_flow.Exceptions;

public class ApproveNotFoundException extends RuntimeException {

    public ApproveNotFoundException() {
        super("Approve request not found");
    }

    public ApproveNotFoundException(String message) {
        super(message);
    }
}
