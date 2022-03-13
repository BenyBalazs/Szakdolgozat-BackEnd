package com.benyovszki.szakdolgozat.rest;

import com.benyovszki.szakdolgozat.dto.request.user.AuthRequest;
import com.benyovszki.szakdolgozat.dto.request.user.UserRegisterRequest;
import com.benyovszki.szakdolgozat.dto.response.user.AuthResponse;
import com.benyovszki.szakdolgozat.dto.response.user.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(RestPaths.BASIC_USER_PATH)
@CrossOrigin("*")
public interface IUserRest {

    @PostMapping(path = "/login")
    ResponseEntity<AuthResponse> authenticateUser(@RequestBody AuthRequest authRequest);

    @PostMapping(path = "/register")
    ResponseEntity<UserResponse> registerUser(@RequestBody UserRegisterRequest user);
}
