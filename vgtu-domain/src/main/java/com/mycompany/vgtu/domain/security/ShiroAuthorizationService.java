package com.mycompany.vgtu.domain.security;

public interface ShiroAuthorizationService {
    boolean isPermittedAllOf(String... permissions);

    boolean isPermittedAnyOf(String... permissions);

    boolean isPermittedToInstantiate(Class<?> clazz);

    void assertThatIsPermittedToInstantiate(Class<?> clazz);
}
