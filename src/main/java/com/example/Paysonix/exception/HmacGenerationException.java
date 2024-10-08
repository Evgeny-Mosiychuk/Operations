package com.example.Paysonix.exception;


public class HmacGenerationException extends RuntimeException {
    public HmacGenerationException(String message) {
        super(message);
    }

    public HmacGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
