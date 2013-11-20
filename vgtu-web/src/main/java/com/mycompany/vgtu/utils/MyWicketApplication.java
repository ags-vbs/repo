package com.mycompany.vgtu.utils;

import com.mycompany.vgtu.security.MyWicketApplicationSecurity;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.mycompany.vgtu.pages.home.HomePage;
import com.mycompany.vgtu.pages.lectures.LecturesListPage;
import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.cycle.PageRequestHandlerTracker;

public class MyWicketApplication extends WebApplication {

    private final Injector injector;
    private final Class<? extends Page> homePageClass = HomePage.class;
    private final Class<? extends Page> loginPageClass = HomePage.class;
    private final Class<? extends Page> logoutPageClass = HomePage.class;
    private final MyWicketApplicationSecurity security;

    @Inject
    public MyWicketApplication(Injector injector) {
        this.injector = injector;
        security = injector.getInstance(MyWicketApplicationSecurity.class);
    }

    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    @Override
    public void init() {
        super.init();
        getComponentInstantiationListeners().add(new GuiceComponentInjector(this, injector));
        getRequestCycleSettings().setResponseRequestEncoding("UTF-8");
        getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
        getRequestCycleListeners().add(new PageRequestHandlerTracker());
        security.attachTo(this, homePageClass, loginPageClass, logoutPageClass);
        mountPages();
    }

    private void mountPages() {
        mountPage("home", HomePage.class);
        mountPackage("lectures", LecturesListPage.class);
//        mountPage("inspection/unplanned/new", UnplannedBuildingInspectionPage.class);
//        mountPage("inspection/new", InspectionNewPage.class);
    }
}
