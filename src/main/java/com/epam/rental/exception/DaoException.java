package com.epam.rental.exception;

public class DaoException extends Exception {

    public DaoException(String message){
        super(message);
    }

    public DaoException(String message, Exception e){
        super(message, e);
    }
}
