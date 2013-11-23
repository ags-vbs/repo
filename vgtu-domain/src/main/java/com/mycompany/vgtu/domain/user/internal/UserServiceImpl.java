package com.mycompany.vgtu.domain.user.internal;

import com.google.common.base.Optional;
import com.mycompany.vgtu.domain.user.UserService;
import com.google.inject.Inject;
import com.mycompany.vgtu.domain.security.PasswordService;
import com.mycompany.vgtu.domain.user.UserJpa;
import com.mycompany.vgtu.domain.security.Permissions;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.ByteSource;

public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;
    @Inject
    private PasswordService passwordService;

    @Override
    public UserJpa saveNewUser(UserJpa user) {
        ByteSource salt = passwordService.generateSalt();
        String hashedPass = passwordService.hashAndSaltPassword(user.getPassword(), salt);
        String saltToDb = passwordService.saltoToString(salt);
        user.setPassword(hashedPass);
        user.setSalt(saltToDb);
        user.setPermissions(Permissions.getAllPermissions());
        return userDao.save(user);
    }

    @Override
    public UserJpa loadById(long id) {
        return userDao.loadById(id);
    }

    @Override
    public Optional<UserJpa> loadByUsername(String username) {
        return userDao.loadByUsername(username);
    }

    @Override
    public Optional<UserJpa> loadCurrentUser() {
        return (Optional<UserJpa>) SecurityUtils.getSubject().getPrincipal();
    }

    @Override
    public boolean isUsernameUnique(String username) {
        return !loadByUsername(username).isPresent();
    }
}
