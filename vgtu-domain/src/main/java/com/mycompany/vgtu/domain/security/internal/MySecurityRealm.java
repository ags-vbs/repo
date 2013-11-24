package com.mycompany.vgtu.domain.security.internal;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.mycompany.vgtu.domain.security.PasswordService;
import com.google.inject.Singleton;
import com.mycompany.vgtu.domain.user.UserJpa;
import com.mycompany.vgtu.domain.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class MySecurityRealm extends AuthorizingRealm implements CredentialsMatcher {

    private static final Logger log = LoggerFactory.getLogger(MySecurityRealm.class);
    @Inject
    private UserService userService;

    public MySecurityRealm() {
        setName(getClass().getSimpleName());
        initCredentialsMatcher();
    }

    private void initCredentialsMatcher() {
        setCredentialsMatcher(this);
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        log.info("authenticating: " + token);
        Optional<UserJpa> user = userService.loadByUsername(userNameFrom(token));
        if (!user.isPresent()) {
            throw new UnknownAccountException("Unknown userName [" + userNameFrom(token) + "]");
        }
        ByteSource salt = ByteSource.Util.bytes(Hex.decode(user.get().getSalt()));
        return new SimpleAuthenticationInfo(user, user.get().getPassword(), salt, getName());
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        HashedCredentialsMatcher authenticator = new HashedCredentialsMatcher(Sha512Hash.ALGORITHM_NAME);
        authenticator.setHashIterations(PasswordService.hashIterations);
        authenticator.setStoredCredentialsHexEncoded(true);
        final boolean successfulAuthentication = authenticator.doCredentialsMatch(token, info);
        return successfulAuthentication;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //TODO: improve permissions. Make annotation with enums.
        Optional<UserJpa> user = userFrom(principals);
        log.info("authorizing: " + user.get());
        return ShiroUtils.authorizationInfoFrom(user.get().getPermissions());
    }

    private String userNameFrom(AuthenticationToken token) {
        return (String) token.getPrincipal();
    }

    private Optional<UserJpa> userFrom(PrincipalCollection principals) {

        //TODO Maybe good maybe not. Need tests if it comes null from principals, but probably yes.
        Optional<UserJpa> user = (Optional<UserJpa>) principals.getPrimaryPrincipal();
        if (user == null) {
            return Optional.absent();
        } else {
            return user;
        }
    }
}
