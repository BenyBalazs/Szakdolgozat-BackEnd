package com.benyovszki.szakdolgozat.service.impl;

import java.util.Optional;

import com.benyovszki.szakdolgozat.exception.EmailAlreadyExistsException;
import com.benyovszki.szakdolgozat.exception.NoUserWithThisUsernameException;
import com.benyovszki.szakdolgozat.exception.UserAlreadyExistsException;
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
    public User saveUser(User userToSave) throws UserAlreadyExistsException {

        if (userRepository.findByUsername(userToSave.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException(userToSave.getUsername());
        }

        if (userRepository.findByEmail(userToSave.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(userToSave.getEmail());
        }

        return userRepository.save(userToSave);
    }

    @Override
    public void setUserRole(String username, Role userRole) throws NoUserWithThisUsernameException {
        Optional<User> userToEdit = userRepository.findByUsername(username);

        if(userToEdit.isEmpty()) {
            throw new NoUserWithThisUsernameException(username);
        }

        User user = userToEdit.get();
        user.setRole(userRole);
        userRepository.save(user);
    }

    @Override
    public User getByUsername(String username) throws NoUserWithThisUsernameException {

        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }
}
