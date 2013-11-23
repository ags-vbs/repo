package com.mycompany.vgtu.domain.user;

import com.mycompany.vgtu.domain.user.internal.UserDao;
import com.google.inject.Inject;
import com.mycompany.vgtu.domain.DaoServiceTestBase;
import java.util.List;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserDaoTest extends DaoServiceTestBase {

    @Inject
    private UserDao userDao;
    private UserJpa savedUser;
    private UserJpa loadedUser;

    public UserDaoTest() {
        injector().injectMembers(this);
    }

    @Override
    protected void extraBeforeActionsAfterTransactionsBegin() {
        savedUser = new UserJpa();
        savedUser.setUsername("testas");
        savedUser.setName("testas");
        savedUser.setPassword("testas");
        savedUser = userDao.save(savedUser);
    }

    @Test
    public void testSomeMethod() {
        List<UserJpa> users = userDao.loadAll();
        assertThat(users.size(), greaterThanOrEqualTo(1));
    }

    @Test
    public void loads_user_by_username() {
        loadedUser = userDao.loadByUsername(savedUser.getUsername()).get();
        assertThat(loadedUser, notNullValue());
    }
}
