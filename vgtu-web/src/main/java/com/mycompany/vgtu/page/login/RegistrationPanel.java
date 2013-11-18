package com.mycompany.vgtu.page.login;

import com.google.inject.Inject;
import com.mycompany.vgtu.domain.security.ShiroAuthenticationService;
import com.mycompany.vgtu.domain.user.UserJpa;
import com.mycompany.vgtu.domain.user.UserService;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class RegistrationPanel extends Panel {

    private static final long serialVersionUID = 1L;
    private TextField<String> usernameField;
    private PasswordTextField passwordField;
    private FeedbackPanel registrationFeedbackPanel;
    @Inject
    private UserService userService;

    public RegistrationPanel(String wicketId) {
        super(wicketId);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        Form form = new Form("registrationForm");
        form.add(initRegistrationUserNameField("username"));
        form.add(initRegistrationPasswordField("password"));
        form.add(initLoginButton("submitButton"));
        form.add(initRegistrationFeedbackPanel("registrationFeedback"));
        add(form);
    }

    private Component initRegistrationUserNameField(String wicketId) {
        usernameField = new TextField<String>(wicketId, new Model<String>());
        usernameField.setRequired(true);
        return usernameField;
    }

    private Component initRegistrationPasswordField(String wicketId) {
        passwordField = new PasswordTextField(wicketId, new Model<String>());
        passwordField.setRequired(true);
        return passwordField;
    }

    private FeedbackPanel initRegistrationFeedbackPanel(String wicketId) {
        registrationFeedbackPanel = new FeedbackPanel(wicketId);
        registrationFeedbackPanel.setOutputMarkupId(true);
        return registrationFeedbackPanel;
    }

    private AjaxButton initLoginButton(String wicketId) {
        return new AjaxButton(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                super.onError(target, form);
                target.add(registrationFeedbackPanel);
            }

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                register(getUsername(), getPassword());
            }
        };
    }

    private String getPassword() {
        return usernameField.getModelObject();
    }

    private String getUsername() {
        return usernameField.getModelObject();
    }

    private void register(String username, String password) {
        UserJpa newUser = new UserJpa();
        newUser.setName(username);
        newUser.setPassword(password);
        userService.saveNewUser(newUser);
        setResponsePage(getApplication().getHomePage());
    }
}
