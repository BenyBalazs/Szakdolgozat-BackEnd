package com.benyovszki.szakdolgozat.rest.impl;

import com.benyovszki.szakdolgozat.action.user.AuthenticationAction;
import com.benyovszki.szakdolgozat.dto.request.user.AuthRequest;
import com.benyovszki.szakdolgozat.action.user.UserRegisterAction;
import com.benyovszki.szakdolgozat.dto.request.user.UserRegisterRequest;
import com.benyovszki.szakdolgozat.dto.response.user.AuthResponse;
import com.benyovszki.szakdolgozat.dto.response.user.UserResponse;
import com.benyovszki.szakdolgozat.rest.IUserRest;
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
    public ResponseEntity<UserResponse> registerUser(UserRegisterRequest user) {
        return userRegisterAction.registerUser(user);
    }
}
