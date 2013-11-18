package com.mycompany.vgtu.domain.user.internal;

import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import com.mycompany.vgtu.domain.user.UserJpa;
import com.mycompany.vgtu.domain.BasicDaoImpl;
import javax.persistence.TypedQuery;
import org.apache.commons.lang.StringUtils;

@Singleton
@Transactional
public class UserDaoImpl extends BasicDaoImpl<UserJpa, Long> implements UserDao {

    public UserDaoImpl() {
        super(UserJpa.class);
    }

    @Override
    public UserJpa loadByUsername(String username) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("select u from ");
        queryBuilder.append(UserJpa.class.getSimpleName());
        queryBuilder.append(" u ");
        queryBuilder.append("where u.username = :username");
        TypedQuery<UserJpa> query = em().createQuery(queryBuilder.toString(), UserJpa.class);
        query.setParameter("username", StringUtils.lowerCase(username));
        return query.getSingleResult();
    }
}
