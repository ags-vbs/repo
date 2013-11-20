package com.mycompany.vgtu.pages.login;

import com.mycompany.vgtu.page.layout.VgtuLayoutPage;
import com.mycompany.vgtu.pages.navbar.MenuItemEnum;

public class RegistrationPage extends VgtuLayoutPage {
    
    private static final long serialVersionUID = 1L;
    
    public RegistrationPage() {
        getContentContainer().setComponent(new RegistrationPanel(getContentContainer().getId()));
    }
    
    @Override
    public MenuItemEnum getActiveMenu() {
        return MenuItemEnum.REGISTER;
    }
}
