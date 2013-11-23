package com.mycompany.vgtu.pages.login;

import com.google.inject.Inject;
import com.mycompany.vgtu.domain.user.UserService;
import com.mycompany.vgtu.page.validators.BasicValidators;
import com.mycompany.vgtu.utils.MyWicketMessages;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;

public class RegistrationPanelValidator extends AbstractFormValidator {

    private static final long serialVersionUID = 1L;
    private MyWicketMessages msg = MyWicketMessages.from(RegistrationPanelValidator.class);
    private FormComponent<String> username;
    private FormComponent<String> password;
    @Inject
    private UserService userService;

    public RegistrationPanelValidator(FormComponent<String> username, FormComponent<String> password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void validate(Form<?> form) {
        if (!form.hasError()) {
            validateRequiredFields();
        }
        if (!form.hasError()) {
            validateUsernameUniquiness();
        }
    }

    @Override
    public FormComponent<?>[] getDependentFormComponents() {
        return null;
    }

    private void validateRequiredFields() {
        if (!BasicValidators.isFieldNotEmpty(username)) {
            username.error(msg.txtModel("emptyUsername").getObject());
        }
        if (!BasicValidators.isFieldNotEmpty(password)) {
            username.error(msg.txtModel("emptyPassword").getObject());
        }
    }

    private void validateUsernameUniquiness() {
        if (!userService.isUsernameUnique(username.getConvertedInput())) {
            username.error(msg.txtModel("duplicateUser").getObject());
        }
    }
}
