package com.mycompany.vgtu.pages.lectures;

import com.mycompany.vgtu.page.layout.VgtuLayoutPage;
import com.mycompany.vgtu.pages.navbar.MenuItemEnum;

public class LecturesListPage extends VgtuLayoutPage {

    private static final long serialVersionUID = 1L;

    public LecturesListPage() {
        getContentContainer().setComponent(new LecturesListPanel(getContentContainer().getId()));
    }

    @Override
    public MenuItemEnum getActiveMenu() {
        return MenuItemEnum.LECTURES;
    }
}
