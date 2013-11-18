package com.mycompany.vgtu.page.layout;

import com.mycompany.vgtu.pages.navbar.TopNavigationPanel;

public class VgtuLayoutPage extends MasterLayoutPage {
    
    private static final long serialVersionUID = 1L;
    
    public VgtuLayoutPage() {
        getHeaderContainer().setComponent(new TopNavigationPanel(getHeaderContainer().getId()));
    }
}
