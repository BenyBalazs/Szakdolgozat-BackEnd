package com.benyovszki.szakdolgozat.service;

import javax.management.relation.RoleNotFoundException;

import com.benyovszki.szakdolgozat.exception.OperationException;
import com.benyovszki.szakdolgozat.model.user.Role;
import com.benyovszki.szakdolgozat.model.user.User;

public interface IUserService {

    User saveUser(User userToSave) throws OperationException;
    void setUserRole(String username, Role userRole) throws OperationException;
    User getByUsername(String username);
    User getByEmail(String email);
}

