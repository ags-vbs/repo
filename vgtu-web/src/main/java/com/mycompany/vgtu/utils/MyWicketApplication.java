package com.mycompany.vgtu.utils;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.mycompany.vgtu.pages.HomePage;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

public class MyWicketApplication extends WebApplication {

    private final Injector injector;
 
    @Inject
    public MyWicketApplication(Injector injector) {
        this.injector = injector;
    }
    
    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    @Override
    public void init() {
        super.init();
        getComponentInstantiationListeners().add(new GuiceComponentInjector(this, injector));
    }
}
