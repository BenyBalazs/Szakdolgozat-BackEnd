package com.benyovszki.szakdolgozat.service.impl;

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
    public User saveUser(User userToSave) {
        return null;
    }

    @Override
    public void addUserRole(User user, Role userRole) {

    }

    @Override
    public void revokeRole(User user, Role UserRole) {

    }

    @Override
    public User getByUsername(String username) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }
}
