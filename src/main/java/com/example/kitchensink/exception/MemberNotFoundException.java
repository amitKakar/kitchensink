package com.example.kitchensink.exception;

/**
 * Exception thrown when a member is not found.
 */
public class MemberNotFoundException extends RuntimeException {

    /**
     * Constructs a new MemberNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public MemberNotFoundException(String message) {
        super(message);
    }
}