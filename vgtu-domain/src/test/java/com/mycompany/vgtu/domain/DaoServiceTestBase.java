package com.mycompany.vgtu.domain;

import com.mycompany.vgtu.domain.modules.DomainTestModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;

public abstract class DaoServiceTestBase {

    private Injector injector;
    @Inject
    private EntityManager em;    

    public DaoServiceTestBase() {
        injector = Guice.createInjector(
                new DomainTestModule());
        injector.injectMembers(this);        
    }

    @Before
    public void setUp() throws Exception {
        em.getTransaction().begin();
        extraBeforeActionsAfterTransactionsBegin();
        em.flush();
    }

    @After
    public void tearDown() throws Exception {
        em.getTransaction().rollback();
    }

    protected Injector injector() {
        return injector;
    }

    protected abstract void extraBeforeActionsAfterTransactionsBegin();
}
