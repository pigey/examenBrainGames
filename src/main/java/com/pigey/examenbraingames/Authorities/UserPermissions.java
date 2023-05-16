package com.pigey.examenbraingames.Authorities;

public enum UserPermissions {

    USER_READ("user:read"),
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write");

    private final String userPermission;

    UserPermissions(String userPermission) {
        this.userPermission = userPermission;
    }

    public String getUserPermission() {
        return userPermission;
    }
}
