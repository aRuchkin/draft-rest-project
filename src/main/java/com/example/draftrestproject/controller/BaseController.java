package com.example.draftrestproject.controller;

import com.example.draftrestproject.error.DataIncorrectException;
import com.example.draftrestproject.error.ErrorResponseModel;
import com.example.draftrestproject.error.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

abstract public class BaseController {


    @ExceptionHandler({ProductNotFoundException.class, DataIncorrectException.class})
    public ResponseEntity<ErrorResponseModel> handleException(RuntimeException e) {
        if (e.getClass().equals(ProductNotFoundException.class)) {
            return createErrorResponse(e, HttpStatus.NOT_FOUND);
        } else {
            return createErrorResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<ErrorResponseModel> createErrorResponse(RuntimeException e, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ErrorResponseModel(httpStatus.getReasonPhrase(), e.getMessage()), httpStatus);
    }

}
