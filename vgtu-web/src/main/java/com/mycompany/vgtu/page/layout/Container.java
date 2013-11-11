package com.mycompany.vgtu.page.layout;

import java.io.Serializable;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;

public class Container implements Serializable {
    private static final long serialVersionUID = 1L;
    private Component part;

    public Container(String componentId) {
        this.part = new Label(componentId, "");
    }

    public Component getComponent() {
        return part;
    }

    public void setComponent(Component replacement) {
        part.replaceWith(replacement);
        part = replacement;
    }

    public String getId() {
        return part.getId();
    }
}