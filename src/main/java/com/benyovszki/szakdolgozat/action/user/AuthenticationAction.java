package com.benyovszki.szakdolgozat.action.user;

import com.benyovszki.szakdolgozat.dto.request.AuthRequest;
import com.benyovszki.szakdolgozat.dto.response.AuthResponse;
import com.benyovszki.szakdolgozat.dto.response.UserResponse;
import com.benyovszki.szakdolgozat.exception.ErrorType;
import com.benyovszki.szakdolgozat.exception.InvalidCredentialsException;
import com.benyovszki.szakdolgozat.exception.OperationException;
import com.benyovszki.szakdolgozat.model.user.User;
import com.benyovszki.szakdolgozat.security.authentication.CustomAuthProvider;
import com.benyovszki.szakdolgozat.security.filter.jwt.JwtUtils;
import com.benyovszki.szakdolgozat.service.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationAction {

    private CustomAuthProvider authenticationManager;
    private UserDetailsService userDetailsService;
    private UserService userService;
    private JwtUtils jwtUtils;

    public ResponseEntity<AuthResponse> authenticate(AuthRequest authRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (OperationException e) {
            throw new InvalidCredentialsException(ErrorType.INVALID_CREDENTIALS, "Bad credentials.");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        User authenticatedUser = userService.getByUsername(userDetails.getUsername());
        AuthResponse response = new AuthResponse();
        response.setToken(jwtUtils.generateToken(userDetails));
        response.setUserDetails(new UserResponse(authenticatedUser.getUsername(), authenticatedUser.getEmail(), authenticatedUser.getRole()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
