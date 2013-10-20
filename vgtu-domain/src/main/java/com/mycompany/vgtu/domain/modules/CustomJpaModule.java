package com.mycompany.vgtu.domain.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

public class CustomJpaModule extends AbstractModule {

    @Override
    protected void configure() {
        installJpaPersistModule();
        startJpaPersistModule();
    }

    private void installJpaPersistModule() {
        JpaPersistModule jpaPersistModule = new JpaPersistModule("vgtuJpaUnit");
        install(jpaPersistModule);
    }

    private void startJpaPersistModule() {
        bind(PersistInitializer.class).asEagerSingleton();
    }

    private static class PersistInitializer {

        @Inject
        public PersistInitializer(final PersistService service) {
            service.start();
        }
    }
}
