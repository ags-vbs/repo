package com.mycompany.vgtu.utils;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.mycompany.vgtu.domain.modules.DomainModule;
import com.mycompany.vgtu.utils.modules.MyServletModule;

public class MyGuiceServletContextListener extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
                new MyServletModule(), 
                new DomainModule());
    }   
}
