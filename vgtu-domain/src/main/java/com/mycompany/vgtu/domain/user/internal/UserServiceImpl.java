package com.mycompany.vgtu.domain.user.internal;

import com.mycompany.vgtu.domain.user.UserService;
import com.google.inject.Inject;
import com.mycompany.vgtu.domain.user.UserJpa;
import org.apache.shiro.SecurityUtils;

public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Override
    public UserJpa save(UserJpa user) {
        return userDao.save(user);
    }

    @Override
    public UserJpa loadById(long id) {
        return userDao.loadById(id);
    }

    @Override
    public UserJpa loadByUsername(String username) {
        return userDao.loadByUsername(username);
    }

    @Override
    public UserJpa loadCurrentUser() {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        return loadByUsername(username);
    }
}
