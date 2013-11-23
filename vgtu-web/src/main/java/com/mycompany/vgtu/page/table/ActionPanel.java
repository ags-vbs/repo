package com.mycompany.vgtu.page.table;

import java.io.Serializable;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public abstract class ActionPanel<T extends Serializable> extends Panel {

    private static final long serialVersionUID = 1L;

    public ActionPanel(String id, IModel<T> model, IModel<String> linkBody) {
        super(id, model);
        Link link = new Link("action") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                onActionClick((T) getParent().getDefaultModelObject());
            }
        };
        link.setBody(linkBody);
        add(link);
    }

    protected abstract void onActionClick(T object);
}
