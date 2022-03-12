package com.benyovszki.szakdolgozat.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@NoArgsConstructor
public class AuthResponse {

    String token;
    UserDetails userDetails;
}
