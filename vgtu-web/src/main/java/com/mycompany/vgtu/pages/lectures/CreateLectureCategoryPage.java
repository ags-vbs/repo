package com.mycompany.vgtu.pages.lectures;

import com.mycompany.vgtu.page.layout.VgtuLayoutPage;
import com.mycompany.vgtu.pages.navbar.MenuItemEnum;

public class CreateLectureCategoryPage extends VgtuLayoutPage {

    private static final long serialVersionUID = 1L;

    public CreateLectureCategoryPage() {
        getContentContainer().setComponent(new CreateLectureCategoryPanel(getContentContainer().getId()));
    }

    @Override
    public MenuItemEnum getActiveMenu() {
        return MenuItemEnum.CREATE_CATEGORY;
    }
}
