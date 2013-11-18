package com.mycompany.vgtu;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mycompany.vgtu.utils.MyWicketApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;

public class WebTestBase {

    private WicketTester tester;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new WebTestModule());
        tester = new WicketTester(new MyWicketApplication(injector));
    }

    public WicketTester tester() {
        return tester;
    }
}
