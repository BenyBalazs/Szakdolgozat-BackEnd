package com.benyovszki.szakdolgozat.action.user;

import com.benyovszki.szakdolgozat.dto.request.AuthRequest;
import com.benyovszki.szakdolgozat.dto.response.AuthResponse;
import com.benyovszki.szakdolgozat.security.authentication.CustomAuthProvider;
import com.benyovszki.szakdolgozat.security.filter.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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
    private JwtUtils jwtUtils;

    public ResponseEntity<AuthResponse> authenticate(AuthRequest authRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        AuthResponse response = new AuthResponse();
        response.setToken(jwtUtils.generateToken(userDetails));
        response.setUserDetails(userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
