package com.benyovszki.szakdolgozat.action.user;

import com.benyovszki.szakdolgozat.action.BaseAction;
import com.benyovszki.szakdolgozat.dto.converter.UserRegistrationConverter;
import com.benyovszki.szakdolgozat.dto.request.user.UserRegisterRequest;
import com.benyovszki.szakdolgozat.dto.response.user.UserResponse;
import com.benyovszki.szakdolgozat.model.user.User;
import com.benyovszki.szakdolgozat.service.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
@AllArgsConstructor
public class UserRegisterAction extends BaseAction {

    private UserService userService;
    private UserRegistrationConverter converter;

    @Transactional
    public ResponseEntity<UserResponse> registerUser(UserRegisterRequest user) {

        if (Objects.isNull(user)) {
            throw missingOrIncompleteParam("Request Parameter is missing");
        }

        if (!StringUtils.hasText(user.getEmail()) || !StringUtils.hasText(user.getUsername()) || !StringUtils.hasText(user.getPassword())) {
            throw missingOrIncompleteParam("No request param shall be empty.");
        }

        User registeredUser = userService.saveUser(converter.toEntity(user));
        UserResponse response = new UserResponse();
        response.setEmail(registeredUser.getEmail());
        response.setUsername(registeredUser.getUsername());
        response.setRole(registeredUser.getRole());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
