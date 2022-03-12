package com.benyovszki.szakdolgozat.model.user;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String roleName;

    Role(String name) {
        this.roleName = name;
    }
    public String getRoleName() {
        return roleName;
    }

    @Override
    public String getAuthority() {
        return roleName.split("_")[1];
    }
}
