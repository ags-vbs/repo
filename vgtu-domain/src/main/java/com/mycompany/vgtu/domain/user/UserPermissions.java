package com.mycompany.vgtu.domain.user;

import java.util.HashSet;
import java.util.Set;

public class UserPermissions {

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    public static Set<String> getAllPermissions() {
        Set<String> permissions = new HashSet<String>();
        permissions.add(ADMIN);
        permissions.add(USER);
        return permissions;
    }
}
