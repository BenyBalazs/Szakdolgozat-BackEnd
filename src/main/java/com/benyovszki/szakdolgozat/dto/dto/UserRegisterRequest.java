package com.benyovszki.szakdolgozat.dto.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisterRequest {

    private String username;
    private String email;
    private String password;
}
