package com.mycompany.vgtu.domain.user.internal;

import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import com.mycompany.vgtu.domain.user.UserJpa;
import com.mycompany.vgtu.domain.BasicDaoImpl;

@Singleton
@Transactional
public class UserDaoImpl extends BasicDaoImpl<UserJpa, Long> implements UserDao{

    public UserDaoImpl() {
        super(UserJpa.class);
    }
    
    @Override
    public UserJpa loadByUsername(String username) {
        return null;        
    };
    

}
