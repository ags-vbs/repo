package com.mycompany.vgtu.domain.security.internal;

import com.google.inject.Singleton;
import com.mycompany.vgtu.domain.security.ShiroAuthorizationService;

@Singleton
public class ShiroAuthorizationServiceImpl implements ShiroAuthorizationService {
    @Override
    public boolean isPermittedAllOf(String... permissions) {
        return ShiroUtils.isPermittedAllOf(permissions);
    }

    @Override
    public boolean isPermittedAnyOf(String... permissions) {
        return ShiroUtils.isPermittedAnyOf(permissions);
    }

    @Override
    public boolean isPermittedToInstantiate(Class<?> clazz) {
        return ShiroUtils.isPermittedToInstantiate(clazz);
    }

    @Override
    public void assertThatIsPermittedToInstantiate(Class<?> clazz) {
        ShiroUtils.assertThatIsPermittedToInstantiate(clazz);
    }
}
