package com.mycompany.vgtu.pages.home;

import com.mycompany.vgtu.page.layout.MasterLayoutPage;
import com.mycompany.vgtu.page.login.UserLoginMenuPanel;

public class HomePage extends MasterLayoutPage {

    public HomePage() {
        getContentContainer().setComponent(new UserLoginMenuPanel(getContentContainer().getId()));
    }
}
