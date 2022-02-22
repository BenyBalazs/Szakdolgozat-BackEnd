package com.benyovszki.szakdolgozat.service;

import com.benyovszki.szakdolgozat.exception.UserAlreadyExistsException;
import com.benyovszki.szakdolgozat.exception.UserAlreadyHasRoleException;
import com.benyovszki.szakdolgozat.exception.UserDoesNotHaveThatRoleException;
import com.benyovszki.szakdolgozat.model.user.Role;
import com.benyovszki.szakdolgozat.model.user.User;

public interface IUserService {

    User saveUser(User userToSave) throws UserAlreadyExistsException;
    void addUserRole(User user, Role userRole) throws UserAlreadyHasRoleException;
    void revokeRole(User user, Role userRole) throws UserDoesNotHaveThatRoleException;
    User getByUsername(String username);
    User getByEmail(String email);
}

