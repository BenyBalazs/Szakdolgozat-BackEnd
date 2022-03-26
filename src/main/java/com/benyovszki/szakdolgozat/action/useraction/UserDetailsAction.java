package com.benyovszki.szakdolgozat.action.useraction;

import com.benyovszki.szakdolgozat.dto.request.user.UserDetailsRequest;
import com.benyovszki.szakdolgozat.dto.response.user.UserResponse;
import com.benyovszki.szakdolgozat.model.user.User;
import com.benyovszki.szakdolgozat.security.filter.jwt.JwtUtils;
import com.benyovszki.szakdolgozat.service.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsAction {

    private UserService userService;
    private JwtUtils jwtUtils;

    public UserResponse getUserDetails(UserDetailsRequest request) {
        System.out.println(request.getToken());
        String username = jwtUtils.extractUsername(request.getToken());
        User user = userService.getByUsername(username);

        return new UserResponse(user.getUsername(), user.getEmail(), user.getRole());
    }
}
