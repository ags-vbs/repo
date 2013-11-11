package com.mycompany.vgtu.domain.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.mycompany.vgtu.domain.calculator.CalculatorDao;
import com.mycompany.vgtu.domain.calculator.internal.CalculatorDaoImpl;
import com.mycompany.vgtu.domain.security.ShiroAuthenticationService;
import com.mycompany.vgtu.domain.security.ShiroAuthorizationService;
import com.mycompany.vgtu.domain.security.internal.MySecurityRealm;
import com.mycompany.vgtu.domain.security.internal.ShiroAuthenticationServiceImpl;
import com.mycompany.vgtu.domain.security.internal.ShiroAuthorizationServiceImpl;
import com.mycompany.vgtu.domain.security.internal.ShiroSecurityInitializerImpl;
import org.apache.shiro.realm.Realm;

@Singleton
public class DomainModule extends AbstractModule {

    @Override
    protected void configure() {
        bindDao();
        bindSecurity();
    }

    private void bindSecurity() {
        bind(Realm.class).to(MySecurityRealm.class);
        bind(ShiroSecurityInitializerImpl.class).asEagerSingleton();
        bind(ShiroAuthenticationService.class).to(ShiroAuthenticationServiceImpl.class);
        bind(ShiroAuthorizationService.class).to(ShiroAuthorizationServiceImpl.class);
    }

    private void bindDao() {
        bind(CalculatorDao.class).to(CalculatorDaoImpl.class);
    }
}
