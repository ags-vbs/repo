package com.mycompany.vgtu.utils.modules;

import com.mycompany.vgtu.domain.security.internal.MySecurityRealm;
import javax.servlet.ServletContext;
import org.apache.shiro.guice.web.ShiroWebModule;

public class MyShiroWebModule extends ShiroWebModule {

    public MyShiroWebModule(ServletContext servletContext) {
        super(servletContext);
    }

    @Override
    protected void configureShiroWeb() {
        bindRealm().to(MySecurityRealm.class);       
    }
}
