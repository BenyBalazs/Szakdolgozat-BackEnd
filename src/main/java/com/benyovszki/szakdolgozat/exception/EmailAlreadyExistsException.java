package com.benyovszki.szakdolgozat.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String email) {
        super(String.format("This e-mail: %s is already in use.", email));
    }
}
