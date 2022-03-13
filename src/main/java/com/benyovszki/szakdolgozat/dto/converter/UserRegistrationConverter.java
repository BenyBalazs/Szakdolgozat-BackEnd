package com.benyovszki.szakdolgozat.dto.converter;

import com.benyovszki.szakdolgozat.dto.request.user.UserRegisterRequest;
import com.benyovszki.szakdolgozat.model.user.Role;
import com.benyovszki.szakdolgozat.model.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserRegistrationConverter implements DtoToEntityConverter<UserRegisterRequest, User> {

    private PasswordEncoder passwordEncoder;

    @Override
    public User toEntity(UserRegisterRequest dto) {

        return User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.ROLE_USER)
                .build();
    }

    @Override
    public UserRegisterRequest toDto(User dest) {
        return UserRegisterRequest.builder()
                .username(dest.getUsername())
                .email(dest.getEmail())
                .password("secret")
                .build();
    }
}
