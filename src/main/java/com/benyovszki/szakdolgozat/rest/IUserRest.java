package com.benyovszki.szakdolgozat.rest;

import com.benyovszki.szakdolgozat.dto.request.AuthRequest;
import com.benyovszki.szakdolgozat.dto.request.UserRegisterRequest;
import com.benyovszki.szakdolgozat.dto.response.AuthResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(RestPaths.BASIC_USER_PATH)
@CrossOrigin("*")
public interface IUserRest {

    @PostMapping(path = "/login")
    ResponseEntity<AuthResponse> authenticateUser(@RequestBody AuthRequest authRequest);

    @PostMapping(path = "/register")
    ResponseEntity<String> registerUser(@RequestBody UserRegisterRequest user);
}
