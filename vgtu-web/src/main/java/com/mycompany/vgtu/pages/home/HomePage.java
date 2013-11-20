package com.mycompany.vgtu.pages.home;

import com.mycompany.vgtu.page.layout.VgtuLayoutPage;
import com.mycompany.vgtu.pages.navbar.MenuItemEnum;
import org.apache.wicket.markup.html.panel.EmptyPanel;

public class HomePage extends VgtuLayoutPage {

    private static final long serialVersionUID = 1L;

    public HomePage() {
        getContentContainer().setComponent(new EmptyPanel(getContentContainer().getId()));
    }

    @Override
    public MenuItemEnum getActiveMenu() {
        return MenuItemEnum.NONE;
    }
}
