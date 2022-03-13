package com.benyovszki.szakdolgozat.service.impl;

import com.benyovszki.szakdolgozat.exception.ErrorType;
import com.benyovszki.szakdolgozat.exception.OperationException;
import com.benyovszki.szakdolgozat.model.user.User;
import com.benyovszki.szakdolgozat.repository.UserRepository;
import com.benyovszki.szakdolgozat.security.authentication.SecurityUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService  {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws OperationException {

        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new OperationException(ErrorType.NO_USER_WITH_THIS_USERNAME, String.format("No user with this username: %s", username));
        }

        return new SecurityUser(user.get());
    }
}
