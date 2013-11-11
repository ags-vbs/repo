package com.mycompany.vgtu.domain.security;

public interface ShiroAuthenticationService {
    void login(String userName, String password);

    void logout();

    void set(LogoutListener logoutListener);

    boolean isAuthenticated();

    boolean authenticationIsRequiredToInstantiate(Class<?> clazz);

    public interface LogoutListener {
        void onLogout();
    }
}
