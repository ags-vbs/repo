package com.mycompany.vgtu.home;

import com.mycompany.vgtu.WebTestBase;
import com.mycompany.vgtu.pages.home.HomePage;
import org.junit.Test;
public class TestHomePage extends WebTestBase{

    @Test
    public void homepageRendersSuccessfully() {
        tester().startPage(HomePage.class);
        tester().assertRenderedPage(HomePage.class);
    }
}
