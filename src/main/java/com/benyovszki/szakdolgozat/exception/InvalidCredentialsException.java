package com.benyovszki.szakdolgozat.exception;

public class InvalidCredentialsException extends OperationException {

    public InvalidCredentialsException(ErrorType errorType, String msg) {
        super(errorType, msg);
    }
}
