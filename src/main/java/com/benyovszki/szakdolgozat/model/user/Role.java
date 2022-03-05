package com.benyovszki.szakdolgozat.model.user;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    USER("USER"),
    ADMIN("ADMIN");

    private final String roleName;

    Role(String name) {
        this.roleName = name;
    }
    public String getRoleName() {
        return roleName;
    }

    @Override
    public String getAuthority() {
        return getRoleName();
    }
}
