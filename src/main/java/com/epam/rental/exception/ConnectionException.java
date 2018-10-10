package com.epam.rental.exception;

public class ConnectionException extends Exception {

    public ConnectionException(String message, Exception e){
        super(message, e);
    }

    public ConnectionException(Exception e){
        super(e);
    }
}
