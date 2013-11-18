package com.mycompany.vgtu.pages.home;

import com.google.inject.Inject;
import com.mycompany.vgtu.domain.security.ShiroAuthenticationService;
import com.mycompany.vgtu.pages.login.LoginPanel;
import com.mycompany.vgtu.pages.login.RegistrationPanel;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;

public class HomeContentPanel extends Panel {

    private static final long serialVersionUID = 1L;
    private WebMarkupContainer container;
    private LoginPanel loginPanel;
    private RegistrationPanel registrationPanel;
    @Inject
    private ShiroAuthenticationService authenticationService;

    public HomeContentPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        container = new WebMarkupContainer("loginRegisterContainer");
        container.setOutputMarkupId(true);
        if (!authenticationService.isAuthenticated()) {
            container.add(initLoginPanel("loginPanel"));
            container.add(initRegistrationPanel("registrationPanel"));
            container.add(getRegistrationLink("registrationLink"));
            container.add(getLoginLink("loginLink"));
        } else {
            container.setVisible(false);
        }
        add(container);
    }

    private Component initLoginPanel(String wicketId) {
        loginPanel = new LoginPanel(wicketId);
        loginPanel.setVisible(false);
        return loginPanel;
    }

    private Component initRegistrationPanel(String wicketId) {
        registrationPanel = new RegistrationPanel(wicketId);
        registrationPanel.setOutputMarkupId(true);
        registrationPanel.setVisible(false);
        return registrationPanel;
    }

    private Component getRegistrationLink(String wicketId) {
        return new AjaxLink(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                registrationPanel.setVisible(true);
                loginPanel.setVisible(false);
                target.add(container);
            }
        };
    }

    private Component getLoginLink(String wicketId) {
        return new AjaxLink(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                registrationPanel.setVisible(false);
                loginPanel.setVisible(true);
                target.add(container);
            }
        };
    }
}
