package com.pigey.examenbraingames.Authorities;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import java.util.stream.Collectors;

public enum UserRoles {

    USER(Set.of(UserPermissions.USER_READ)),
    ADMIN(Set.of(UserPermissions.ADMIN_READ, UserPermissions.ADMIN_WRITE));

    private final Set<UserPermissions> permissionsList;

    <E> UserRoles(Set<UserPermissions> permissionsList) {
        this.permissionsList = permissionsList;
    }

    public Set<UserPermissions> getPermissions() {
        return permissionsList;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){

        Set<SimpleGrantedAuthority> permissionsSet = getPermissions().stream().map(
                index -> new SimpleGrantedAuthority(index.getUserPermission())).collect(Collectors.toSet());
        permissionsSet.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissionsSet;
    }

}

