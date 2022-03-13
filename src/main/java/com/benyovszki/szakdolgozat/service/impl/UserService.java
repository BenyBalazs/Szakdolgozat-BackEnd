package com.benyovszki.szakdolgozat.service.impl;

import java.util.Optional;

import com.benyovszki.szakdolgozat.exception.ErrorType;
import com.benyovszki.szakdolgozat.exception.OperationException;
import com.benyovszki.szakdolgozat.model.user.Role;
import com.benyovszki.szakdolgozat.model.user.User;
import com.benyovszki.szakdolgozat.repository.UserRepository;
import com.benyovszki.szakdolgozat.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private UserRepository userRepository;

    @Override
    public User saveUser(User userToSave) throws OperationException {

        if (userRepository.findByUsername(userToSave.getUsername()).isPresent()) {
            throw new OperationException(ErrorType.USERNAME_TAKEN, String.format("No user with this username: %s", userToSave.getUsername()));
        }

        if (userRepository.findByEmail(userToSave.getEmail()).isPresent()) {
            throw new OperationException(ErrorType.EMAIL_ALREADY_IN_USE, String.format("No user with this username: %s", userToSave.getUsername()));
        }

        return userRepository.save(userToSave);
    }

    @Override
    public void setUserRole(String username, Role userRole) throws OperationException {
        Optional<User> userToEdit = userRepository.findByUsername(username);

        if(userToEdit.isEmpty()) {
            throw new OperationException(ErrorType.USERNAME_TAKEN, String.format("Username is taken: %s", username));
        }

        User user = userToEdit.get();
        user.setRole(userRole);
        userRepository.save(user);
    }

    @Override
    public User getByUsername(String username) throws OperationException {

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new OperationException(ErrorType.NO_USER_WITH_THIS_USERNAME, String.format("No user with this username:  %s", username)));
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new OperationException(ErrorType.EMAIL_DOES_NOT_EXISTS, String.format("Email does not exists:  %s", email)));
    }
}
