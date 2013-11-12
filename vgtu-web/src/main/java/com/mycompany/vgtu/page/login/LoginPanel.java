package com.mycompany.vgtu.page.login;

import com.google.inject.Inject;
import com.mycompany.vgtu.domain.security.ShiroAuthenticationService;
import com.mycompany.vgtu.domain.user.UserJpa;
import com.mycompany.vgtu.domain.user.UserService;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class LoginPanel extends Panel {

    private static final long serialVersionUID = 1L;
    // State
    private UserJpa user;
    private FeedbackPanel loginFeedbackPanel;
    private DropDownChoice userDropDownChoice;
    private PasswordTextField passwordField;
    // Services
    @Inject
    private UserService userService;
    @Inject
    private ShiroAuthenticationService authenticationService;

    public LoginPanel(String wicketId) {
        super(wicketId);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(initLoginFeedbackPanel("login-feedback"));
        add(initLoginForm("login-form"));
    }

    private FeedbackPanel initLoginFeedbackPanel(String wicketId) {
        loginFeedbackPanel = new FeedbackPanel(wicketId);
        loginFeedbackPanel.setOutputMarkupId(true);
        return loginFeedbackPanel;
    }

    private Form initLoginForm(String wicketId) {
        Form loginForm = new Form(wicketId);
        loginForm.add(initUserDropDownChoice("username"));
        loginForm.add(initPasswordField("password"));
        loginForm.add(initLoginButton("button"));
        return loginForm;
    }

    private DropDownChoice initUserDropDownChoice(String wicketId) {
        IChoiceRenderer<User> choiceRenderer = new IChoiceRenderer<User>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Object getDisplayValue(User user) {
                Person person = user.getPerson();
                String display = person.getFullName();
                if (isClient(person)) {
                    if (person.getCompanyId() == null) {
                        return display += " (...)";
                    }
                    String companyName = companyService.loadById(person.getCompanyId()).getName();
                    return display + " (" + companyName.substring(0, Math.min(15, companyName.length() - 1)) + "...)";
                }
                return display;
            }

            @Override
            public String getIdValue(User user, int index) {
                return user.getId().toString();
            }
        };
        userDropDownChoice = new DropDownChoice(wicketId, new PropertyModel(this, "user"), userService.loadAll(), choiceRenderer);
        userDropDownChoice.setNullValid(true);
        return userDropDownChoice;
    }

    private PasswordTextField initPasswordField(String wicketId) {
        passwordField = new PasswordTextField(wicketId, Model.of(""));
        // Disable default required message, we have our own validator
        passwordField.setRequired(false);
        return passwordField;
    }

    private AjaxButton initLoginButton(String wicketId) {
        return new AjaxButton(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                login();
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(loginFeedbackPanel);
            }
        };
    }

    private void login() {
        continueToOriginalDestination();
        setResponsePage(getApplication().getHomePage());
    }
}
