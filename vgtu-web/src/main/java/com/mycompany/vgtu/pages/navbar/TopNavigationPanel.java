package com.mycompany.vgtu.pages.navbar;

import com.google.inject.Inject;
import com.mycompany.vgtu.domain.security.ShiroAuthenticationService;
import com.mycompany.vgtu.domain.user.UserJpa;
import com.mycompany.vgtu.domain.user.UserService;
import com.mycompany.vgtu.pages.lectures.LecturesPage;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class TopNavigationPanel extends Panel {

    private static final long serialVersionUID = 1L;
    private UserJpa user;
    @Inject
    private ShiroAuthenticationService authenticationService;
    @Inject
    private UserService userService;
    private WebMarkupContainer loggedUserContainer;

    public TopNavigationPanel(String wicketId) {
        super(wicketId);
        this.user = userService.loadCurrentUser();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(getLecturesLink("lecturesLink"));
        add(initLoggedUserContainer("loggedUserContainer"));
    }

    private Component initLoggedUserContainer(String wicketId) {
        loggedUserContainer = new WebMarkupContainer(wicketId);
        if (authenticationService.isAuthenticated()) {
            loggedUserContainer.add(getUsernameLabel("usernameLabel"));
            loggedUserContainer.add(getLogoutLink("logoutLink"));
            loggedUserContainer.setVisible(true);
        } else {
            loggedUserContainer.setVisible(false);
        }
        return loggedUserContainer;
    }

    private Label getUsernameLabel(String wicketId) {
        return new Label(wicketId, Model.of(user.getUsername()));
    }

    private Link getLogoutLink(String wicketId) {
        return new Link(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                authenticationService.logout();
            }
        };
    }

    private Component getLecturesLink(String wicketId) {
        return new Link(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                setResponsePage(LecturesPage.class);
            }
        };
    }
}