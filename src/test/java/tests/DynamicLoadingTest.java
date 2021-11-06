package tests;

import base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DynamicLoadingPage;

import static page.HomePage.Links.DYNAMIC_LOADING_PAGE;

public class DynamicLoadingTest extends BaseTest {
    private DynamicLoadingPage dynamicLoadingPage;

    @BeforeEach
    @Override
    public void minorSetUp() {
        super.minorSetUp();
        dynamicLoadingPage = (DynamicLoadingPage) homePage
                .navigateTo(DYNAMIC_LOADING_PAGE);
    }

    @Test
    public void testNavigationToTheDynamicLoadingPage() {
        dynamicLoadingPage
                .assertOnPageTitle("Dynamically Loaded Page Elements");
    }

    @Test
    public void exOneEnsureThatResultWillDisplayAfterClickingOnStart() {
        dynamicLoadingPage
                .navigateToExampleOne()
                .clickOnStart()
                .assertResultIsDisplayed();
    }

    @Test
    public void exTwoEnsureThatResultWillDisplayAfterClickingOnStart() {
        dynamicLoadingPage
                .navigateToExampleTwo()
                .clickOnStart()
                .assertResultIsDisplayed();
    }
}
