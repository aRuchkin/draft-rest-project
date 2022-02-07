package com.example.draftrestproject.error;

public class ErrorResponseModel {
    private final String errorCode;
    private final String errorMessage;

    public ErrorResponseModel(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
