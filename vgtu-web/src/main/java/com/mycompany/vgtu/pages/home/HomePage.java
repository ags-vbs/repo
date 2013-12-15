package com.mycompany.vgtu.pages.home;

import com.mycompany.vgtu.page.layout.VgtuLayoutPage;
import com.mycompany.vgtu.pages.navbar.MenuItemEnum;
import com.mycompany.vgtu.utils.MyWicketMessages;
import org.apache.wicket.markup.html.basic.Label;

public class HomePage extends VgtuLayoutPage {

    private static final long serialVersionUID = 1L;

    private final MyWicketMessages messages = MyWicketMessages.from(this);

    public HomePage() {
        getContentContainer().setComponent(getContentLabel(getContentContainer().getId()));
    }

    @Override
    public MenuItemEnum getActiveMenu() {
        return MenuItemEnum.NONE;
    }

    private Label getContentLabel(String wicketId) {
        Label label = new Label(wicketId, messages.txtModel("infoAboutSiteLabel"));
        label.setEscapeModelStrings(false);
        return label;
    }
}
