package com.epam.rental.exception;

public class BuilderException extends Exception {
    public BuilderException(String message, Exception e) {
        super(message, e);
    }
}
