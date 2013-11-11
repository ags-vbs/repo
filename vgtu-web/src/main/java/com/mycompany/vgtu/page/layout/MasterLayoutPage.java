package com.mycompany.vgtu.page.layout;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public abstract class MasterLayoutPage extends WebPage {
    private static final long serialVersionUID = 1L;
    // Layout's parts
    private Container headerContainer = new Container("header");
    private Container menuContainer = new Container("menu");
    private Container contentContainer = new Container("content");
    private Container footerContainer = new Container("footer");

    public MasterLayoutPage() {
        this(null);
    }

    public MasterLayoutPage(PageParameters pageParameters) {
        super(pageParameters);
        add(headerContainer.getComponent());
        add(menuContainer.getComponent());
        add(contentContainer.getComponent());
        add(footerContainer.getComponent());
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
    }

    public Container getHeaderContainer() {
        return headerContainer;
    }

    protected Container getMenuContainer() {
        return menuContainer;
    }

    protected Container getContentContainer() {
        return contentContainer;
    }

    protected Container getFooterContainer() {
        return footerContainer;
    }
}
