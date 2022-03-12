package com.benyovszki.szakdolgozat.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisterRequest {

    private String username;
    private String email;
    private String password;
}
