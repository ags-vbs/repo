package com.mycompany.vgtu.page.login;

import com.google.inject.Inject;
import com.mycompany.vgtu.domain.security.ShiroAuthenticationService;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;

public class UserPanel extends Panel {

    private static final long serialVersionUID = 1L;
    @Inject
    private ShiroAuthenticationService authenticationService;

    public UserPanel(String wicketId) {
        super(wicketId);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(getUserLoginMenuPanel("userLoginMenuPanel"));
    }

    private Component getUserLoginMenuPanel(String wicketId) {
        if (authenticationService.isAuthenticated()) {
            return new UserInfoPanel(wicketId);
        } else {
            return new LoginPanel(wicketId);
        }
    }
}