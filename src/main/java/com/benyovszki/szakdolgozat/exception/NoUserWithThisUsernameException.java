package com.benyovszki.szakdolgozat.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class NoUserWithThisUsernameException extends UsernameNotFoundException {

    public NoUserWithThisUsernameException(String username) {
        super(String.format("No user is fount with this username: %s", username));
    }
}
