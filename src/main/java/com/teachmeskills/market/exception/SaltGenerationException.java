package com.teachmeskills.market.exception;

public class SaltGenerationException extends RuntimeException {
    public SaltGenerationException(String message) {
        super(message);
    }

    public SaltGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}