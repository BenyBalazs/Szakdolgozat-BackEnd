package com.benyovszki.szakdolgozat.dto.request.user;

import lombok.Data;

@Data
public class AuthRequest {

    private String username;
    private String password;
}
