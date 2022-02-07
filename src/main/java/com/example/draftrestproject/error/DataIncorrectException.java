package com.example.draftrestproject.error;

/**
 * Create/update product exception (incorrect request data)
 */
public class DataIncorrectException extends RuntimeException {
    public DataIncorrectException(String message) {
        super(message);
    }
}
