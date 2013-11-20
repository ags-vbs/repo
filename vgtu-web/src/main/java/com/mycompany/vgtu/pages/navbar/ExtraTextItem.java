package com.mycompany.vgtu.pages.navbar;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public abstract class ExtraTextItem extends Panel {

    private static final long serialVersionUID = 1L;

    public ExtraTextItem(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(getExtraTextLabel("extraText"));
    }

    private Component getExtraTextLabel(String wicketId) {
        return new Label(wicketId, getExtraText());
    }

    protected abstract String getExtraText();
}
