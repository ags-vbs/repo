package com.mycompany.vgtu.utils.modules;

import com.google.inject.persist.PersistFilter;
import com.google.inject.servlet.ServletModule;
import com.mycompany.vgtu.domain.modules.DomainModule;
import com.mycompany.vgtu.domain.modules.CustomJpaModule;

public class CustomServletModule extends ServletModule {
    @Override
    protected void configureServlets() {
        install(new CustomJpaModule());
        install(new DomainModule());
        filter("/*").through(PersistFilter.class);
    }
}
    

