package com.mycompany.vgtu.domain.security.internal;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.ThreadContext;

@Singleton
public class ShiroSecurityInitializerImpl {

    @Inject
    public void initForCurrentThread(Realm realm) {
        DefaultSecurityManager securityManager = new DefaultSecurityManager(realm);
        securityManager.setCacheManager(new MemoryConstrainedCacheManager());
        ThreadContext.bind(securityManager);
        SecurityUtils.setSecurityManager(securityManager);
    }
}
