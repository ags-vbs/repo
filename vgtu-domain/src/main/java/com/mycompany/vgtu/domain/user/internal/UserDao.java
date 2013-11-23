package com.mycompany.vgtu.domain.user.internal;

import com.google.common.base.Optional;
import com.mycompany.vgtu.domain.BasicDao;
import com.mycompany.vgtu.domain.user.UserJpa;

public interface UserDao extends BasicDao<UserJpa, Long> {

    Optional<UserJpa> loadByUsername(String username);
}
