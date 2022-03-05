package com.benyovszki.szakdolgozat.rest.impl;

import com.benyovszki.szakdolgozat.action.user.AuthenticationAction;
import com.benyovszki.szakdolgozat.dto.dto.AuthRequest;
import com.benyovszki.szakdolgozat.action.user.UserRegisterAction;
import com.benyovszki.szakdolgozat.dto.dto.UserRegisterRequest;
import com.benyovszki.szakdolgozat.rest.IUserRest;
import com.benyovszki.szakdolgozat.rest.RestPaths;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(RestPaths.BASIC_USER_PATH)
public class UserRest implements IUserRest {

    private UserRegisterAction userRegisterAction;
    private AuthenticationAction authenticationAction;

    @PostMapping(path = "/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterRequest user) {
        return userRegisterAction.registerUser(user);
    }

    @PostMapping(path = "/login")
    ResponseEntity<String> authenticateUser(@RequestBody AuthRequest authRequest) {
        return authenticationAction.authenticate(authRequest);
    }
}
