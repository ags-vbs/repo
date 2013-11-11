package com.mycompany.vgtu.domain.security.internal;

import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import com.mycompany.vgtu.domain.security.ShiroAuthenticationService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

@Singleton
@Transactional
public class ShiroAuthenticationServiceImpl implements ShiroAuthenticationService {

    private LogoutListener logoutListener;

    @Override
    public boolean isAuthenticated() {
        return subject().isAuthenticated();
    }

    @Override
    public boolean authenticationIsRequiredToInstantiate(Class<?> clazz) {
        return ShiroUtils.authentificationIsRequiredToInstantiate(clazz);
    }

    protected static Subject subject() {
        return SecurityUtils.getSubject();
    }

    @Override
    public void login(String userName, String password) {
        subject().login(new UsernamePasswordToken(userName, password));
    }

    @Override
    public final void logout() {
        subject().logout();
        if (logoutListener != null) {
            logoutListener.onLogout();
        }
    }

    @Override
    public void set(LogoutListener logoutListener) {
        if (isAttemptToChangeCurrent(logoutListener)) {
            throw new RuntimeException("Attempt to change current " + LogoutListener.class.getSimpleName()
                    + "\n    from: " + this.logoutListener
                    + "\n    to  : " + logoutListener);
        }
        this.logoutListener = logoutListener;
    }

    private boolean isAttemptToChangeCurrent(LogoutListener logoutListener) {
        return this.logoutListener != null && this.logoutListener != logoutListener;
    }
}
