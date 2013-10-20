package com.mycompany.vgtu.domain.calculator;

import com.google.inject.Inject;
import com.mycompany.vgtu.domain.DaoTestBase;
import java.util.List;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class CalculatorTest extends DaoTestBase {

    @Inject
    private CalculatorDao calculatorDao;

    public CalculatorTest() {
        injector().injectMembers(this);
    }

    @Override
    protected void extraBeforeActionsAfterTransactionsBegin() {
        CalculatorJpa calc = new CalculatorJpa("anykas", "betkas");
        calculatorDao.save(calc);
    }

    @Test
    public void testSomeMethod() {
        List<CalculatorJpa> calculatorJpas = calculatorDao.loadAll();
        assertThat(calculatorJpas.size(), greaterThanOrEqualTo(1));
    }
}