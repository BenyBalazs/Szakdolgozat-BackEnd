package com.benyovszki.szakdolgozat.service;

import javax.management.relation.RoleNotFoundException;

import com.benyovszki.szakdolgozat.exception.UserAlreadyExistsException;
import com.benyovszki.szakdolgozat.exception.UserAlreadyHasRoleException;
import com.benyovszki.szakdolgozat.exception.UserDoesNotHaveThatRoleException;
import com.benyovszki.szakdolgozat.model.user.Role;
import com.benyovszki.szakdolgozat.model.user.User;

public interface IUserService {

    User saveUser(User userToSave) throws UserAlreadyExistsException;
    void setUserRole(String username, Role userRole) throws UserAlreadyHasRoleException, RoleNotFoundException;
    User getByUsername(String username);
    User getByEmail(String email);
}

