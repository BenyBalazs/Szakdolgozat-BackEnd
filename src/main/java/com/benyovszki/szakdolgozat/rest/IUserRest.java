package com.benyovszki.szakdolgozat.rest;

import com.benyovszki.szakdolgozat.dto.request.UserRegisterRequest;
import org.springframework.http.ResponseEntity;

public interface IUserRest {

    ResponseEntity<String> registerUser( UserRegisterRequest user);
}
