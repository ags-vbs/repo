package com.mycompany.vgtu.utils;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.servlet.GuiceServletContextListener;
import com.mycompany.vgtu.domain.modules.DomainModule;
import com.mycompany.vgtu.domain.security.Permissions;
import com.mycompany.vgtu.domain.user.UserJpa;
import com.mycompany.vgtu.domain.user.UserService;
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
        Injector injector = Guice.createInjector(
                new MyServletModule(),
                new DomainModule(),
                new MyShiroWebModule(servletContext),
                new ShiroAopModule(),
                new MyWebModule());

        executeApplicationStartupTasks(injector);
        return injector;
    }
//FIX ME. Find out about persist service. Does it is good to start it here and 
    //dont stop or better is commented way in MyServletModule which start auto this service: filter("/*").through(PersistFilter.class);

    private void executeApplicationStartupTasks(Injector injector) {
        startPersistService(injector);
        saveDefaultUsers(injector);
    }
//FIX ME: Move these methods somewhere else, not here.

    private void startPersistService(Injector injector) {
        PersistService service = injector.getInstance(PersistService.class);
        service.start();
    }

    private void saveDefaultUsers(Injector injector) {
        UserService userService = injector.getInstance(UserService.class);
        String name = "admin11";
        String pasw = "admin11";
        if (userService.isUsernameUnique(name)) {
            UserJpa userJpa = new UserJpa();
            userJpa.setUsername(name);
            userJpa.setPassword(pasw);
            userJpa.setPermissions(Permissions.getAllPermissions());
            userService.saveNewUser(userJpa);
        } else {
            //user default already exist
        }
    }
}
