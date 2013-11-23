package com.mycompany.vgtu.page.layout;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.mycompany.vgtu.domain.security.Permissions;
import com.mycompany.vgtu.domain.security.ShiroAuthenticationService;
import com.mycompany.vgtu.domain.security.ShiroAuthorizationService;
import com.mycompany.vgtu.domain.user.UserJpa;
import com.mycompany.vgtu.domain.user.UserService;
import com.mycompany.vgtu.pages.lectures.CreateLectureCategoryPage;
import com.mycompany.vgtu.pages.lectures.CreateLecturePage;
import com.mycompany.vgtu.pages.lectures.LecturesListPage;
import com.mycompany.vgtu.pages.login.LoginPage;
import com.mycompany.vgtu.pages.login.RegistrationPage;
import com.mycompany.vgtu.pages.navbar.ExtraLinkItem;
import com.mycompany.vgtu.pages.navbar.ExtraTextItem;
import com.mycompany.vgtu.pages.navbar.MenuItemEnum;
import com.mycompany.vgtu.pages.navbar.TwitterBootstrapNavBarPanel;
import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.StringResourceModel;

public class VgtuTopMenuPanel extends Panel {

    private static final long serialVersionUID = 1L;
    private Class<? extends Page> homePage;
    private String applicationName;
    private MenuItemEnum activeMenuItem;
    @Inject
    private ShiroAuthenticationService authenticationService;
    @Inject
    private ShiroAuthorizationService authorizationService;
    @Inject
    private UserService userService;
    private final boolean hasAdminRights;

    public VgtuTopMenuPanel(String id, Class<? extends Page> homePage, String applicationName, MenuItemEnum activeMenuItem) {
        super(id);
        this.homePage = homePage;
        this.applicationName = applicationName;
        this.activeMenuItem = activeMenuItem;
        this.hasAdminRights = authorizationService.isPermittedAllOf(Permissions.ADMIN);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(getTopMenuPanel("menuPanel"));
    }

    public Component getTopMenuPanel(String wciketId) {
        return new TwitterBootstrapNavBarPanel.Builder(wciketId, homePage, applicationName, activeMenuItem)
                .withMenuItem(MenuItemEnum.LOGIN, LoginPage.class, !authenticationService.isAuthenticated())
                .withMenuItem(MenuItemEnum.REGISTER, RegistrationPage.class, !authenticationService.isAuthenticated())
                .withMenuItem(MenuItemEnum.LECTURES, LecturesListPage.class, true)
                .withMenuItem(MenuItemEnum.CREATE_LECTURE, CreateLecturePage.class, hasAdminRights)
                .withMenuItem(MenuItemEnum.CREATE_CATEGORY, CreateLectureCategoryPage.class, hasAdminRights)
                //                .withMenuItemAsDropdown(MenuItemEnum.PRODUCTS, ProductOnePage.class, "Product One")
                //                .withMenuItemAsDropdown(MenuItemEnum.PRODUCTS, ProductTwoPage.class, "Product Two")
                //                .withMenuItemAsDropdown(MenuItemEnum.PRODUCTS, ProductTwoPage.class, "Product Three")
                //                .withMenuItemAsDropdown(MenuItemEnum.ABOUT_US, TeamPage.class, "Team")
                //                .withMenuItemAsDropdown(MenuItemEnum.ABOUT_US, OurSkillsPage.class, "Our Skills")
                //                .withMenuItem(MenuItemEnum.CONTACT, ContactPage.class)            
                .witExtraBootStrapImageRight("userIcon", "glyphicon glyphicon-user", authenticationService.isAuthenticated(), 2)
                .withExtraTextItemRight(getLoggedUserText(), authenticationService.isAuthenticated(), 20)
                .witExtraBootStrapImageRight("logOutIcon", "glyphicon glyphicon-log-out", authenticationService.isAuthenticated(), 2)
                .withExtraLinkItemRight(getLogoutLink(), authenticationService.isAuthenticated(), 0)
                .build();
    }

    private ExtraLinkItem getLogoutLink() {
        return new ExtraLinkItem("userInfo") {
            private static final long serialVersionUID = 1L;

            @Override
            protected String getExtraLinkBody() {
                return new StringResourceModel("logout", this, null).getString();
            }

            @Override
            protected void onExtraLinkClick() {
                authenticationService.logout();
            }
        };
    }

    private ExtraTextItem getLoggedUserText() {
        return new ExtraTextItem("userText") {
            private static final long serialVersionUID = 1L;

            @Override
            protected String getExtraText() {
                Optional<UserJpa> currentUser = userService.loadCurrentUser();
                if (currentUser.isPresent()) {
                    String text = new StringResourceModel("connectedAs", this, null).getString()
                            + currentUser.get().getUsername().toUpperCase();
                    return text;
                } else {
                    return null;
                }
            }
        };
    }
}
