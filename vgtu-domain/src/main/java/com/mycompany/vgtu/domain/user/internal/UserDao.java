package com.mycompany.vgtu.domain.user.internal;

import com.mycompany.vgtu.domain.BasicDao;
import com.mycompany.vgtu.domain.user.UserJpa;

public interface UserDao extends BasicDao<UserJpa, Long> {

    UserJpa loadByUsername(String username);
}
