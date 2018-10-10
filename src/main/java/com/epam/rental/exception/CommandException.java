package com.epam.rental.exception;

public class CommandException extends Throwable {
    public CommandException(Exception e) {
        super(e);
    }

    public CommandException(String message, Exception e) {
        super(message, e);
    }
}
