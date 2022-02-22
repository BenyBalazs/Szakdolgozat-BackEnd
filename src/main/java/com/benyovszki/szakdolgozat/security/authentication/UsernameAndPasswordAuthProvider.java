package com.benyovszki.szakdolgozat.security.authentication;

import java.util.List;
import java.util.Optional;

import com.benyovszki.szakdolgozat.exception.NoUserWithThisUsernameException;
import com.benyovszki.szakdolgozat.model.user.User;
import com.benyovszki.szakdolgozat.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UsernameAndPasswordAuthProvider implements AuthenticationProvider {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Optional<User> user = userRepository.findByUsername(authentication.getName());

        if(user.isEmpty()) {
            throw new NoUserWithThisUsernameException(authentication.getName());
        }

        if(passwordEncoder.matches(authentication.getCredentials().toString(), user.get().getPassword())) {
            return new UsernamePasswordAuthenticationToken(user.get().getUsername(), user.get().getPassword(), List.of(user.get().getUserRole()));
        }
        else {
            throw new BadCredentialsException("No user found with these credentials");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
