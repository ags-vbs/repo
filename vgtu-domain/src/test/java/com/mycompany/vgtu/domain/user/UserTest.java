package com.mycompany.vgtu.domain.user;

import com.mycompany.vgtu.domain.user.internal.UserDao;
import com.google.inject.Inject;
import com.mycompany.vgtu.domain.DaoServiceTestBase;
import java.util.List;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserTest extends DaoServiceTestBase {

    @Inject
    private UserDao userDao;

    public UserTest() {
        injector().injectMembers(this);
    }

    @Override
    protected void extraBeforeActionsAfterTransactionsBegin() {
        UserJpa user = new UserJpa();
        user.setName("testas");
        user.setPassword("testas");
        userDao.save(user);
    }

    @Test
    public void testSomeMethod() {
        List<UserJpa> users = userDao.loadAll();
        assertThat(users.size(), greaterThanOrEqualTo(1));
    }
}
