package com.mycompany.vgtu.pages.login;

import com.google.inject.Inject;
import com.mycompany.vgtu.domain.security.ShiroAuthenticationService;
import com.mycompany.vgtu.pages.lectures.LecturesListPage;
import com.mycompany.vgtu.utils.MyWicketMessages;
import org.apache.shiro.authc.AuthenticationException;
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
    private MyWicketMessages messages = MyWicketMessages.from(this);
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
        form.add(new LoginPanelValidator(usernameField, passwordField));
        add(form);
    }

    private Component initLoginUserNameField(String wicketId) {
        usernameField = new TextField<String>(wicketId, new Model<String>());
        return usernameField;
    }

    private Component initLoginPasswordField(String wicketId) {
        passwordField = new PasswordTextField(wicketId, new Model<String>());
        passwordField.setRequired(false);
        return passwordField;
    }

    private FeedbackPanel initLoginFeedbackPanel(String wicketId) {
        loginFeedbackPanel = new FeedbackPanel(wicketId);
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
        try {
            authenticationService.login(username, password);
        } catch (AuthenticationException ex) {
            loginFeedbackPanel.error(messages.txtModel("badCredentials"));
        }
        //FIXME: do not do validation like this... Maybe... Somewhhere else do it or somehow different.
        if (!loginFeedbackPanel.anyErrorMessage()) {
            continueToOriginalDestination();
            setResponsePage(LecturesListPage.class);
        }
    }
}
