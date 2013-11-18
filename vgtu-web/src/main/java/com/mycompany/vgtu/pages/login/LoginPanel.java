package com.mycompany.vgtu.pages.login;

import com.google.inject.Inject;
import com.mycompany.vgtu.domain.security.ShiroAuthenticationService;
import com.mycompany.vgtu.page.layout.MyFeedbackPanel;
import com.mycompany.vgtu.pages.lectures.LecturesPage;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class LoginPanel extends Panel {

    private static final long serialVersionUID = 1L;
    private TextField<String> usernameField;
    private PasswordTextField passwordField;
    private FeedbackPanel loginFeedbackPanel;
    @Inject
    private ShiroAuthenticationService authenticationService;

    public LoginPanel(String wicketId) {
        super(wicketId);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        Form form = new Form("loginForm");
        form.add(initLoginUserNameField("username"));
        form.add(initLoginPasswordField("password"));
        form.add(initLoginButton("submitButton"));
        form.add(initLoginFeedbackPanel("loginFeedback"));
        add(form);
    }

    private Component initLoginUserNameField(String wicketId) {
        usernameField = new TextField<String>(wicketId, new Model<String>());
        usernameField.setRequired(true);
        return usernameField;
    }

    private Component initLoginPasswordField(String wicketId) {
        passwordField = new PasswordTextField(wicketId, new Model<String>());
        passwordField.setRequired(true);
        return passwordField;
    }

    private FeedbackPanel initLoginFeedbackPanel(String wicketId) {
        loginFeedbackPanel = new MyFeedbackPanel(wicketId);
        loginFeedbackPanel.setOutputMarkupId(true);
        return loginFeedbackPanel;
    }

    private AjaxButton initLoginButton(String wicketId) {
        return new AjaxButton(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                super.onError(target, form);
                target.add(loginFeedbackPanel);
            }

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                login(getUsername(), getPassword());
            }
        };
    }

    private String getPassword() {
        return usernameField.getModelObject();
    }

    private String getUsername() {
        return usernameField.getModelObject();
    }

    private void login(String username, String password) {
        authenticationService.login(username, password);
        continueToOriginalDestination();
        setResponsePage(LecturesPage.class);
    }
}
