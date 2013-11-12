package com.mycompany.vgtu.page.login;

import com.google.inject.Inject;
import com.mycompany.vgtu.domain.security.ShiroAuthenticationService;
import com.mycompany.vgtu.page.layout.Container;
import org.apache.wicket.markup.html.panel.Panel;

public class UserInfoPanel extends Panel {
    private static final long serialVersionUID = 1L;
    private final Container userInfoPanel = new Container("user-info-panel");
    @Inject
    private ShiroAuthenticationService authenticationService;

    public UserInfoPanel(String wicketId) {
        super(wicketId);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(userInfoPanel.getComponent());
    }
    
    @Override
    protected void onConfigure() {
        super.onConfigure();
        initUserInfoPanel();
    }

    private void initUserInfoPanel() {
        if (authenticationService.isAuthenticated()) {
            userInfoPanel.setComponent(new AuthenticatedUserInfoPanel(userInfoPanel.getId()));
        } else {
            userInfoPanel.setComponent(new NotAuthenticatedUserInfoPanel(userInfoPanel.getId()));
        }
    }
}