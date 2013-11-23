package com.mycompany.vgtu.page.validators;

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.util.string.Strings;

public class BasicValidators {

    //FIX ME. This class maybe is not usefull and its design including name and logic are wrong.
    public static boolean isFieldNotEmpty(FormComponent<?> component) {
        final String input = component.getInput();

        // when null, check whether this is natural for that component, or
        // whether - as is the case with text fields - this can only happen
        // when the component was disabled
        if (input == null && !component.isInputNullable() && !component.isEnabledInHierarchy()) {
            // this value must have come from a disabled field
            // do not perform validation
            return true;
        }

        // peform validation by looking whether the value is null or empty
        return !Strings.isEmpty(input);
    }
}
