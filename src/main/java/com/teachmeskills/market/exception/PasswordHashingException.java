package com.teachmeskills.market.exception;

public class PasswordHashingException extends RuntimeException {
    public PasswordHashingException(String message) {
        super(message);
    }

    public PasswordHashingException(String message, Throwable cause) {
        super(message, cause);
    }
}