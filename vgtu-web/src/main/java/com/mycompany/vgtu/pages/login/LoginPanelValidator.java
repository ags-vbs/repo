package com.mycompany.vgtu.pages.login;

import com.mycompany.vgtu.page.validators.BasicValidators;
import com.mycompany.vgtu.utils.MyWicketMessages;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;

public class LoginPanelValidator extends AbstractFormValidator {

    private static final long serialVersionUID = 1L;
    private MyWicketMessages msg = MyWicketMessages.from(LoginPanelValidator.class);
    private FormComponent<String> username;
    private FormComponent<String> password;

    public LoginPanelValidator(FormComponent<String> username, FormComponent<String> password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void validate(Form<?> form) {
        if (!form.hasError()) {
            validateRequiredFields();
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
}
