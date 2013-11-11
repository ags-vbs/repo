package com.mycompany.vgtu.utils.modules;

import com.google.inject.Inject;
import com.google.inject.binder.AnnotatedBindingBuilder;
import com.mycompany.vgtu.domain.security.internal.MySecurityRealm;
import java.util.Collection;
import javax.servlet.ServletContext;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.guice.web.ShiroWebModule;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;

public class MyShiroWebModule extends ShiroWebModule {

    public MyShiroWebModule(ServletContext servletContext) {
        super(servletContext);
    }

    @Override
    protected void configureShiroWeb() {
        bindRealm().to(MySecurityRealm.class);
        bind(Initializer.class).asEagerSingleton();
       
    }

    @Override
    protected void bindWebSecurityManager(AnnotatedBindingBuilder<? super WebSecurityManager> bind) {
        try {
            bind(DefaultWebSecurityManager.class).toConstructor(DefaultWebSecurityManager.class.getConstructor(Collection.class));
            bind.to(DefaultWebSecurityManager.class).asEagerSingleton();
        } catch (NoSuchMethodException e) {
            throw new ConfigurationException("This really shouldn't happen.  Either something has changed in Shiro, or there's a bug in ShiroModule.", e);
        }
    }

    private static class Initializer {

        @Inject
        Initializer(DefaultWebSecurityManager securityManager) {
            securityManager.setCacheManager(new MemoryConstrainedCacheManager());
        }
    }
}
