package com.benyovszki.szakdolgozat.rest;

import com.benyovszki.szakdolgozat.dto.request.user.UserDetailsRequest;
import com.benyovszki.szakdolgozat.dto.response.user.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(RestPaths.USER_ADMINISTRATION_PATH)
@CrossOrigin("*")
public interface IUserActions {

    @PostMapping("/check")
    ResponseEntity<UserResponse> getCheckUserDetails(@RequestBody UserDetailsRequest detailsRequest);
}
