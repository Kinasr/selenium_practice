package tests;

import base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DynamicControlsPage;

import static page.HomePage.Links.DYNAMIC_CONTROLS_PAGE;

public class DynamicControlsTest extends BaseTest {
    private DynamicControlsPage dynamicControlsPage;

    @BeforeEach
    @Override
    public void minorSetUp() {
        super.minorSetUp();
        dynamicControlsPage = ((DynamicControlsPage) homePage
                .navigateTo(DYNAMIC_CONTROLS_PAGE));
    }

    @Test
    public void testNavigationToTheDynamicControlsPage() {
        dynamicControlsPage
                .assertOnPageTitle("Dynamic Controls");
    }

    @Test
    public void removeTheCheckbox() {
        dynamicControlsPage
                .clickOnAddRemoveButton()
                .assertOnCheckboxMessage("It's gone!");
    }

    @Test
    public void removeTheCheckboxThenAddIt() {
        dynamicControlsPage
                .clickOnAddRemoveButton()
                .clickOnAddRemoveButton()
                .assertOnCheckboxMessage("It's back!")
                .assertThatCheckboxIsAppear();
    }

    @Test
    public void enableTextField() {
        dynamicControlsPage
                .clickOnEnableDisableButton()
                .assertOnTextFieldMessage("It's enabled!")
                .assertTheTextFieldStatus(true);
    }

    @Test
    public void enableTextFieldThenDisableIt() {
        dynamicControlsPage
                .clickOnEnableDisableButton()
                .clickOnEnableDisableButton()
                .assertOnTextFieldMessage("It's disabled!")
                .assertTheTextFieldStatus(false);
    }
}
