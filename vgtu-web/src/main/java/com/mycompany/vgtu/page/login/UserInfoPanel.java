package com.mycompany.vgtu.page.login;

import com.google.inject.Inject;
import com.mycompany.vgtu.domain.security.ShiroAuthenticationService;
import com.mycompany.vgtu.domain.user.UserJpa;
import com.mycompany.vgtu.domain.user.UserService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class UserInfoPanel extends Panel {
    private static final long serialVersionUID = 1L;
    private UserJpa user;
    @Inject
    private ShiroAuthenticationService authenticationService;
    @Inject
    private UserService userService;

    public UserInfoPanel(String wicketId) {
        super(wicketId);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        this.user = userService.loadCurrentUser();
        add(initUsernameLabel("usernameLabel"));
        add(initLogoutLink("logoutLink"));
    }

    private Label initUsernameLabel(String wicketId) {
        return new Label(wicketId, Model.of(user.getUsername()));
    }

    private Link initLogoutLink(String wicketId) {
        return new Link(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                authenticationService.logout();
            }
        };
    }
}
