package com.benyovszki.szakdolgozat.dto.response;

import com.benyovszki.szakdolgozat.model.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    String username;
    String email;
    Role role;
}
