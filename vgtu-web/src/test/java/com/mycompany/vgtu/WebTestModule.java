package com.mycompany.vgtu;

import com.google.inject.AbstractModule;
import com.mycompany.vgtu.domain.modules.DomainTestModule;


public class WebTestModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new DomainTestModule());
    }
}
