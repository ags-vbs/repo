package com.mycompany.vgtu.domain.modules;

import com.mycompany.vgtu.domain.modules.DomainModule;
import com.google.inject.AbstractModule;


public class DomainTestModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new DomainModule());
        install(new TestJpaModule());
    }
}
