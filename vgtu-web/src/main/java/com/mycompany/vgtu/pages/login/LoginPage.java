package com.mycompany.vgtu.pages.login;

import com.mycompany.vgtu.page.layout.VgtuLayoutPage;
import com.mycompany.vgtu.pages.navbar.MenuItemEnum;

public class LoginPage extends VgtuLayoutPage {
    
    private static final long serialVersionUID = 1L;
    
    public LoginPage() {
        getContentContainer().setComponent(new LoginPanel(getContentContainer().getId()));
    }
    
    @Override
    public MenuItemEnum getActiveMenu() {
        return MenuItemEnum.LOGIN;
    }
}
