package com.mycompany.vgtu.pages.lectures;

import com.mycompany.vgtu.page.layout.VgtuLayoutPage;
import com.mycompany.vgtu.pages.navbar.MenuItemEnum;
import org.apache.shiro.authz.annotation.RequiresAuthentication;

public class LecturesPage extends VgtuLayoutPage {

    private static final long serialVersionUID = 1L;

    public LecturesPage() {
        getContentContainer().setComponent(new LecturesListPanel(getContentContainer().getId()));
    }

    @Override
    public MenuItemEnum getActiveMenu() {
        return MenuItemEnum.LECTURES;
    }
}
