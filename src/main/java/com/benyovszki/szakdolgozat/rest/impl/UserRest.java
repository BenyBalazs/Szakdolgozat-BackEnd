package com.benyovszki.szakdolgozat.rest.impl;

import com.benyovszki.szakdolgozat.action.user.AuthenticationAction;
import com.benyovszki.szakdolgozat.dto.request.AuthRequest;
import com.benyovszki.szakdolgozat.action.user.UserRegisterAction;
import com.benyovszki.szakdolgozat.dto.request.UserRegisterRequest;
import com.benyovszki.szakdolgozat.dto.response.AuthResponse;
import com.benyovszki.szakdolgozat.rest.IUserRest;
import com.benyovszki.szakdolgozat.rest.RestPaths;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserRest implements IUserRest {

    private UserRegisterAction userRegisterAction;
    private AuthenticationAction authenticationAction;


    @Override
    public ResponseEntity<AuthResponse> authenticateUser(AuthRequest authRequest) {
        return authenticationAction.authenticate(authRequest);
    }

    @Override
    public ResponseEntity<String> registerUser(UserRegisterRequest user) {
        return userRegisterAction.registerUser(user);
    }
}
