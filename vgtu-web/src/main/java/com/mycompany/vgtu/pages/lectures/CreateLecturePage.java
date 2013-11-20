package com.mycompany.vgtu.pages.lectures;

import com.mycompany.vgtu.page.layout.VgtuLayoutPage;
import com.mycompany.vgtu.pages.navbar.MenuItemEnum;

public class CreateLecturePage extends VgtuLayoutPage {

    private static final long serialVersionUID = 1L;

    public CreateLecturePage() {
        getContentContainer().setComponent(new CreateLecturePanel(getContentContainer().getId()));
    }

    @Override
    public MenuItemEnum getActiveMenu() {
        return MenuItemEnum.CREATE_LECTURE;
    }
}
