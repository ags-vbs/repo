package com.mycompany.vgtu.utils;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.mycompany.vgtu.domain.modules.DomainModule;
import com.mycompany.vgtu.utils.modules.MyShiroWebModule;
import com.mycompany.vgtu.utils.modules.MyServletModule;
import com.mycompany.vgtu.utils.modules.MyWebModule;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import org.apache.shiro.guice.aop.ShiroAopModule;

public class MyGuiceServletContextListener extends GuiceServletContextListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(final ServletContextEvent servletContextEvent) {
        this.servletContext = servletContextEvent.getServletContext();
        super.contextInitialized(servletContextEvent);
    }

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
                new MyServletModule(),
                new DomainModule(),
                new MyShiroWebModule(servletContext),
                new ShiroAopModule(),
                new MyWebModule());
    }
}
