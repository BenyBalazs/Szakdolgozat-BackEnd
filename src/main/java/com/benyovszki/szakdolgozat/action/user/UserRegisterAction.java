package com.benyovszki.szakdolgozat.action.user;

import com.benyovszki.szakdolgozat.dto.converter.UserRegistrationConverter;
import com.benyovszki.szakdolgozat.dto.request.UserRegisterRequest;
import com.benyovszki.szakdolgozat.exception.EmailAlreadyExistsException;
import com.benyovszki.szakdolgozat.exception.UserAlreadyExistsException;
import com.benyovszki.szakdolgozat.service.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
@AllArgsConstructor
public class UserRegisterAction {

    private UserService userService;
    private UserRegistrationConverter converter;

    public ResponseEntity<String> registerUser(UserRegisterRequest user) {

        if (Objects.isNull(user)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty request");
        }

        if (!StringUtils.hasText(user.getEmail()) || !StringUtils.hasText(user.getUsername()) || !StringUtils.hasText(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing Parameters");
        }

        try {
            userService.saveUser(converter.toEntity(user));
            return ResponseEntity.status(HttpStatus.OK).body("New user is registered and ready to use");
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(String.format("User with this username: %s already exist.", user.getUsername()));
        } catch (EmailAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(String.format("User with this email: %s already exist.", user.getEmail()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong;");
        }

    }

}
