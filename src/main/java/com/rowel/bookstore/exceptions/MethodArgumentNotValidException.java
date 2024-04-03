package com.rowel.bookstore.exceptions;

import org.springframework.validation.Errors;

public class MethodArgumentNotValidException extends RuntimeException {

    public MethodArgumentNotValidException(String message) {
        super(message);
    }


}