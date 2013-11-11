package com.mycompany.vgtu.domain.security.internal;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.authz.aop.AuthenticatedAnnotationHandler;
import org.apache.shiro.authz.aop.AuthorizingAnnotationHandler;
import org.apache.shiro.authz.aop.GuestAnnotationHandler;
import org.apache.shiro.authz.aop.PermissionAnnotationHandler;
import org.apache.shiro.authz.aop.RoleAnnotationHandler;
import org.apache.shiro.authz.aop.UserAnnotationHandler;

public class ShiroUtils {

    @SuppressWarnings("unchecked")
    private static final List<Class<? extends Annotation>> AUTHENTIFICATION_ANNOTATIONS = Arrays.asList(
            RequiresAuthentication.class,
            RequiresPermissions.class,
            RequiresRoles.class,
            RequiresUser.class);
    private static final AuthorizingAnnotationHandler[] ANNOTATION_HANDLERS = new AuthorizingAnnotationHandler[]{
        new AuthenticatedAnnotationHandler(),
        new GuestAnnotationHandler(),
        new PermissionAnnotationHandler(),
        new RoleAnnotationHandler(),
        new UserAnnotationHandler()
    };

    public static boolean authentificationIsRequiredToInstantiate(Class<?> clazz) {
        return isAuthentificationRequiredFor(annotationsFor(clazz));
    }

    public static boolean isAuthentificationRequiredFor(Collection<Annotation> annotations) {
        for (Annotation annotation : annotations) {
            for (Class<? extends Annotation> annotationClass : AUTHENTIFICATION_ANNOTATIONS) {
                if (annotationClass.isInstance(annotation)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void assertThatIsPermittedToInstantiate(Class<?> clazz) {
        try {
            List<Annotation> annotations = annotationsFor(clazz);
            for (Annotation annotation : annotations) {
                for (AuthorizingAnnotationHandler handler : ANNOTATION_HANDLERS) {
                    handler.assertAuthorized(annotation);
                }
            }
        } catch (UnauthorizedException ex) {
            throw new UnauthorizedException("Can not instantiate " + clazz + ": " + ex.getMessage());
        }
    }

    public static boolean isPermittedToInstantiate(Class<?> clazz) {
        try {
            assertThatIsPermittedToInstantiate(clazz);
            return true;
        } catch (AuthorizationException ex) {
            return false;
        }
    }

    public static AuthorizationInfo authorizationInfoFrom(final Collection<String> stringPermissions) {
        return new AuthorizationInfo() {
            private static final long serialVersionUID = 1L;

            @Override
            public Collection<String> getRoles() {
                return Collections.EMPTY_LIST;
            }

            @Override
            public Collection<String> getStringPermissions() {
                return stringPermissions;
            }

            @Override
            public Collection<Permission> getObjectPermissions() {
                return Collections.EMPTY_LIST;
            }
        };
    }

    public static boolean isPermittedAllOf(String[] permissions) {
        return SecurityUtils.getSubject().isPermittedAll(permissions);
    }

    static boolean isPermittedAnyOf(String[] permissions) {
        boolean[] permitted = SecurityUtils.getSubject().isPermitted(permissions);
        for (int i = 0; i < permitted.length; i++) {
            if (permitted[i]) {
                return true;
            }
        }
        return false;
    }

    private static List<Annotation> annotationsFor(final Class<?> clazz) {
        List<Annotation> result = null;
        Class<?> currentClass = clazz;
        while (currentClass != null) {
            Annotation[] declaredAnnotations = currentClass.getDeclaredAnnotations();
            if (declaredAnnotations.length > 0) {
                if (result == null) {
                    result = new ArrayList<Annotation>(3);
                }
                result.addAll(Arrays.asList(declaredAnnotations));
            }
            currentClass = currentClass.getSuperclass();
        }
        return result != null ? result : Collections.EMPTY_LIST;
    }
}
