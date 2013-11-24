package com.mycompany.vgtu.domain.security;

import java.util.HashSet;
import java.util.Set;

public class Permissions {
    //FIX ME. Permissions should be improved, maybe not string, but enum and methods should be not so stupid.

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    public static Set<String> getAllPermissions() {
        Set<String> permissions = new HashSet<String>();
        permissions.add(ADMIN);
        permissions.add(USER);
        return permissions;
    }

    public static Set<String> getSimpleUserPermissions() {
        Set<String> permissions = new HashSet<String>();
        permissions.add(USER);
        return permissions;
    }
}
