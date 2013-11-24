package com.mycompany.vgtu.pages.lectures;

import com.mycompany.vgtu.domain.lecture.CategoryJpa;
import com.mycompany.vgtu.page.validators.BasicValidators;
import com.mycompany.vgtu.utils.MyWicketMessages;
import com.mycompany.vgtu.utils.UrlUtils;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;

public class CreateLecturePanelValidator extends AbstractFormValidator {

    private static final long serialVersionUID = 1L;
    private static MyWicketMessages msg = MyWicketMessages.from(CreateLecturePanel.class);
    private FormComponent<String> name;
    private FormComponent<String> description;
    private FormComponent<String> url;
    private FormComponent<CategoryJpa> category;

    public CreateLecturePanelValidator(FormComponent<String> name, FormComponent<String> description, FormComponent<String> url, FormComponent<CategoryJpa> category) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.category = category;
    }

    @Override
    public void validate(Form<?> form) {
        if (!form.hasError()) {
            validateRequiredFields();
        }
        if (!form.hasError()) {
            validateVideoUrl();
        }
    }

    @Override
    public FormComponent<?>[] getDependentFormComponents() {
        return null;
    }

    private void validateRequiredFields() {
        if (!BasicValidators.isFieldNotEmpty(name)) {
            name.error(msg.txtModel("emptyName").getObject());
        }
        if (!BasicValidators.isFieldNotEmpty(description)) {
            description.error(msg.txtModel("emptyDescription").getObject());
        }

        if (!BasicValidators.isFieldNotEmpty(url)) {
            url.error(msg.txtModel("emptyUrl").getObject());
        }
        if (!BasicValidators.isFieldNotEmpty(category)) {
            category.error(msg.txtModel("emptyCategory").getObject());
        }
    }

    private void validateVideoUrl() {
        if (!UrlUtils.isYoutubeUrlValid(url.getConvertedInput())) {
            url.error(msg.txtModel("wrongUrl").getObject());
        }
    }
}
