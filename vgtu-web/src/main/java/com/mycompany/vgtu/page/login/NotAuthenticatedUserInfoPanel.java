package com.mycompany.vgtu.page.login;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;

public class NotAuthenticatedUserInfoPanel extends Panel {
    private static final long serialVersionUID = 1L;
    private Component loginLink;

    public NotAuthenticatedUserInfoPanel(String wicketId) {
        super(wicketId);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        loginLink = new PageLinkWithText("loginLink", LoginPage.class, msg.model("login"));
        loginLink.setOutputMarkupId(true);
        add(loginLink);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        loginLink.setVisible(!(getPage() instanceof LoginPage));
    }
}
