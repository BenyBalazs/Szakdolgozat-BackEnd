package com.benyovszki.szakdolgozat.action.user;

import com.benyovszki.szakdolgozat.dto.dto.AuthRequest;
import com.benyovszki.szakdolgozat.security.jwt.JwtUtils;
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

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtUtils jwtUtils;

    public ResponseEntity<String> authenticate(AuthRequest authRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid login credentials");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        return ResponseEntity.status(HttpStatus.OK).body(jwtUtils.generateToken(userDetails));
    }
}
