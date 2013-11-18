package com.mycompany.vgtu.pages.home;

import com.mycompany.vgtu.page.layout.MasterLayoutPage;
import com.mycompany.vgtu.page.login.UserPanel;

public class HomePage extends MasterLayoutPage {

    public HomePage() {
        getHeaderContainer().setComponent(new UserPanel(getHeaderContainer().getId()));
        getContentContainer().setComponent(new HomeContentPanel(getContentContainer().getId()));
    }
}
