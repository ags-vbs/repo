package com.mycompany.vgtu.pages.navbar;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public abstract class ExtraLinkItem extends Panel {

    private static final long serialVersionUID = 1L;

    public ExtraLinkItem(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(getExtraLink("extraLink"));

    }

    private Component getExtraLink(String wicketId) {
        Link link = new Link(wicketId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                onExtraLinkClick();
            }
        };
        link.setBody(Model.of(getExtraLinkBody()));
        return link;
    }

    protected abstract String getExtraLinkBody();

    protected abstract void onExtraLinkClick();
}
