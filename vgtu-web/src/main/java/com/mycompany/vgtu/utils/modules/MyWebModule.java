package com.mycompany.vgtu.utils.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.mycompany.vgtu.security.MyWicketApplicationSecurity;

@Singleton
public class MyWebModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MyWicketApplicationSecurity.class);
    }
}
