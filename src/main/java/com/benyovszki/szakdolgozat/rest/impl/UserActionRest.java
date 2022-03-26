package com.benyovszki.szakdolgozat.rest.impl;

import com.benyovszki.szakdolgozat.action.useraction.UserDetailsAction;
import com.benyovszki.szakdolgozat.dto.request.user.UserDetailsRequest;
import com.benyovszki.szakdolgozat.dto.response.user.UserResponse;
import com.benyovszki.szakdolgozat.rest.IUserActions;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserActionRest implements IUserActions {

    private UserDetailsAction userDetailsAction;

    @Override
    public ResponseEntity<UserResponse> getCheckUserDetails(UserDetailsRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userDetailsAction.getUserDetails(request));
    }
}
