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
    private UserDao calculatorDao;

    public UserTest() {
        injector().injectMembers(this);
    }

    @Override
    protected void extraBeforeActionsAfterTransactionsBegin() {
        UserJpa calc = new UserJpa("test", "testas", "testauskas");
        calculatorDao.save(calc);
    }

    @Test
    public void testSomeMethod() {
        List<UserJpa> calculatorJpas = calculatorDao.loadAll();
        assertThat(calculatorJpas.size(), greaterThanOrEqualTo(1));
    }
}