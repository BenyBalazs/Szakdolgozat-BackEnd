package com.benyovszki.szakdolgozat.dto.response.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthResponse {

    private String token;
    private UserResponse userDetails;
}
