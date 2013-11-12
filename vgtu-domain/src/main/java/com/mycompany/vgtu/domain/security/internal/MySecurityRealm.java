package com.mycompany.vgtu.domain.security.internal;

import com.google.inject.Inject;
import com.mycompany.vgtu.domain.security.PasswordService;
import com.google.inject.Singleton;
import com.mycompany.vgtu.domain.user.UserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
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
//        log.info("authenticating: " + token);
//        User user = userService.loadByUsername(userNameFrom(token));
//        if (user == null) {
//            throw new UnknownAccountException("Unknown userName [" + userNameFrom(token) + "]");
//        }
//        ByteSource salt = ByteSource.Util.bytes(Hex.decode(user.getSalt()));
//        return new SimpleAuthenticationInfo(user, user.getPassword(), salt, getName());
        return null;
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
//        User user = userFrom(principals);
//        log.info("authorizing: " + user);
//        return ShiroUtils.authorizationInfoFrom(userService.getShiroPermissionsForAuthorizationByUserId(user.getId()));
         return null;
    }

    private String userNameFrom(AuthenticationToken token) {
        return (String) token.getPrincipal();
    }
}
