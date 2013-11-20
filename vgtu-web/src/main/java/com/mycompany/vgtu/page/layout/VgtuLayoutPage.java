package com.mycompany.vgtu.page.layout;

import com.mycompany.vgtu.pages.navbar.MenuItemEnum;

public abstract class VgtuLayoutPage extends MasterLayoutPage {

    private static final long serialVersionUID = 1L;

    public VgtuLayoutPage() {
        getHeaderContainer().setComponent(new VgtuTopMenuPanel(getHeaderContainer().getId(), getApplication().getHomePage(), "VGTU", getActiveMenu()));
    }

    public abstract MenuItemEnum getActiveMenu();
}
