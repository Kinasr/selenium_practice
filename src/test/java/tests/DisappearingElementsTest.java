package tests;

import base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.DisappearingElementsPage;

import static page.HomePage.Links.DISAPPEARING_ELEMENTS_PAGE;

public class DisappearingElementsTest extends BaseTest {
    private final String PAGE_TITLE = "Disappearing Elements";
    private DisappearingElementsPage disappearingElementsPage;

    @BeforeEach
    @Override
    public void minorSetUp() {
        super.minorSetUp();
        disappearingElementsPage = (DisappearingElementsPage) homePage
                .navigateTo(DISAPPEARING_ELEMENTS_PAGE);
    }

    @Test
    @DisplayName(value = "Ensure that all buttons are present in the page after navigating to it")
    public void ensureThatAllButtonsAppearsProperlyInThePage() {
        disappearingElementsPage
                .assertOnPageTitle(PAGE_TITLE)
                .ensureThatAllButtonsArePresent();
    }

    @Test
    @DisplayName(value = "Ensure that all buttons are present in the page after navigating to Home page" +
            " then navigate again to the page")
    public void ensureThatAllButtonsAppearsProperlyEvenAfterGoingToHomePageThenEnterPageAgain() {
        ((DisappearingElementsPage) disappearingElementsPage
                .goToHomePage()
                .navigateTo(DISAPPEARING_ELEMENTS_PAGE))
                .assertOnPageTitle(PAGE_TITLE)
                .ensureThatAllButtonsArePresent();

    }
}
