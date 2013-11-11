package com.mycompany.vgtu.security;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mycompany.vgtu.domain.security.ShiroAuthenticationService;
import com.mycompany.vgtu.domain.security.ShiroAuthenticationService.LogoutListener;
import com.mycompany.vgtu.domain.security.ShiroAuthorizationService;
import java.io.Serializable;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.component.IRequestableComponent;

@Singleton
public class MyWicketApplicationSecurity implements Serializable {

    private static final long serialVersionUID = 1L;
    private final LogoutListener logoutListener;
    private Class<? extends Page> homePageClass;
    private Class<? extends Page> loginPageClass;
    private Class<? extends Page> logoutPageClass;
    @Inject
    private ShiroAuthenticationService authenticationService;
    @Inject
    private ShiroAuthorizationService authorizationService;

    public MyWicketApplicationSecurity() {
        logoutListener = new LogoutListener() {
            @Override
            public void onLogout() {
                doLogout();
            }
        };
    }

    public void attachTo(WebApplication application,
            final Class<? extends Page> homePageClass,
            final Class<? extends Page> loginPageClass,
            final Class<? extends Page> logoutPageClass) {
        initPageClasses(application, homePageClass, loginPageClass, logoutPageClass);
        initAuthorizationStrategyFor(application);
        authenticationService.set(logoutListener);
    }

    private void initPageClasses(
            WebApplication application,
            Class<? extends Page> homePageClass,
            Class<? extends Page> loginPageClass,
            Class<? extends Page> logoutPageClass) {
        this.homePageClass = homePageClass;
        this.loginPageClass = loginPageClass;
        this.logoutPageClass = logoutPageClass;
        if (this.homePageClass == null) {
            this.homePageClass = application.getHomePage();
        }
        if (this.loginPageClass == null) {
            this.loginPageClass = this.homePageClass;
        }
        if (this.logoutPageClass == null) {
            this.logoutPageClass = this.homePageClass;
        }
    }

    private void initAuthorizationStrategyFor(WebApplication application) {
        application.getSecuritySettings().setAuthorizationStrategy(new IAuthorizationStrategy() {
            @Override
            public <T extends IRequestableComponent> boolean isInstantiationAuthorized(Class<T> componentClass) {
                return isPermittedToInstantiate(componentClass);
            }

            @Override
            public boolean isActionAuthorized(Component component, Action action) {
                return true;
            }
        });
    }

    private <T extends IRequestableComponent> boolean isPermittedToInstantiate(Class<T> componentClass) {
        if (authentificationIsRequiredToInstantiate(componentClass) && !isAuthenticated()) {
            throw new RestartResponseAtInterceptPageException(loginPageClass);

        }
        if (isLoginPage(componentClass) && isAuthenticated() && !isLoginPageSameAsHomePage()) {
            throw new RestartResponseException(homePageClass);
        }
        authorizationService.assertThatIsPermittedToInstantiate(componentClass);
        return true;
    }

    private void doLogout() {
        Session.get().invalidate();
        throw new RestartResponseException(logoutPageClass);
    }

    private boolean authentificationIsRequiredToInstantiate(Class<?> componentClass) {
        return authenticationService.authenticationIsRequiredToInstantiate(componentClass);
    }

    private boolean isAuthenticated() {
        return authenticationService.isAuthenticated();
    }

    private boolean isLoginPage(Class<? extends IRequestableComponent> componentClass) {
        return loginPageClass.equals(componentClass);
    }

    private boolean isLoginPageSameAsHomePage() {
        return loginPageClass.equals(homePageClass);
    }
}
