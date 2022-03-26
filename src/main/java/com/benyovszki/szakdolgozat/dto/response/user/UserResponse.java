package com.benyovszki.szakdolgozat.dto.response.user;

import com.benyovszki.szakdolgozat.model.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String username;
    private String email;
    private Role role;
}
