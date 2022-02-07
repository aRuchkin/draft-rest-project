package com.example.draftrestproject.error;

public class DataIncorrectException extends RuntimeException {
    public DataIncorrectException(String message) {
        super(message);
    }
}
