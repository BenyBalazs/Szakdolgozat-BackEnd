package com.benyovszki.szakdolgozat.rest;

import com.benyovszki.szakdolgozat.dto.dto.UserRegisterRequest;
import org.springframework.http.ResponseEntity;

public interface IUserRest {

    ResponseEntity<String> registerUser( UserRegisterRequest user);
}
