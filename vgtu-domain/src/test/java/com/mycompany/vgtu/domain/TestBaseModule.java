package com.mycompany.vgtu.domain;

import com.mycompany.vgtu.domain.modules.DomainModule;
import com.google.inject.AbstractModule;
import com.mycompany.vgtu.domain.modules.CustomJpaModule;


public class TestBaseModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new DomainModule());
        install(new CustomJpaModule());
    }
}
