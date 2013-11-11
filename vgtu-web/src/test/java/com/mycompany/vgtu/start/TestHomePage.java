package com.mycompany.vgtu.start;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mycompany.vgtu.WebTestModule;
import com.mycompany.vgtu.pages.home.HomePage;
import com.mycompany.vgtu.utils.MyWicketApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage {

    private WicketTester tester;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new WebTestModule());
        tester = new WicketTester(new MyWicketApplication(injector));
    }

    @Test
    public void homepageRendersSuccessfully() {
        tester.startPage(HomePage.class);
        tester.assertRenderedPage(HomePage.class);
    }
}
