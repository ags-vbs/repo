package com.mycompany.vgtu.pages.home;

import com.mycompany.vgtu.page.layout.VgtuLayoutPage;

public class HomePage extends VgtuLayoutPage {
    private static final long serialVersionUID = 1L;

    public HomePage() {
        getContentContainer().setComponent(new HomeContentPanel(getContentContainer().getId()));
    }
}
