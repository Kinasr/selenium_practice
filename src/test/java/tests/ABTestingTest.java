package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import page.ABTestingPage;

import static page.HomePage.Links.AB_TESTING_PAGE;

public class ABTestingTest extends BaseTest {

    @Test
    public void testNavigationToABTestingPage() {
        var abTestingPage = (ABTestingPage) homePage
                .navigateTo(AB_TESTING_PAGE);

        abTestingPage
                .assertOnPageTitle("A/B Test Control");
    }
}
