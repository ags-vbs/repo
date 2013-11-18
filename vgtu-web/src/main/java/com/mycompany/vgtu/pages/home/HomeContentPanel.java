package com.mycompany.vgtu.pages.home;

import com.mycompany.vgtu.page.login.LoginPanel;
import com.mycompany.vgtu.page.login.RegistrationPanel;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Panel;

public class HomeContentPanel extends Panel {

    public HomeContentPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(getLoginPanel("loginPanel"));
        add(getRegistrationPanel("registrationPanel"));
        add(getRegistrationLink("registrationLink"));
        add(getLoginLink("loginLink"));
    }

    private Component getLoginPanel(String wicketId) {
        LoginPanel loginPanel = new LoginPanel(wicketId);
        return loginPanel;
    }

    private Component getRegistrationPanel(String wicketId) {
        RegistrationPanel registrationPanel = new RegistrationPanel(wicketId);
        registrationPanel.setVisible(false);
        return registrationPanel;
    }

    private Component getRegistrationLink(String wicketId) {
        return new AjaxLink(wicketId) {

            @Override
            public void onClick(AjaxRequestTarget target) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    private Component getLoginLink(String wicketId) {
        return new AjaxLink(wicketId) {

            @Override
            public void onClick(AjaxRequestTarget target) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        };
    }

}
