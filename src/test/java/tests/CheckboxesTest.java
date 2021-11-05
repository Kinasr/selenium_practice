package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import page.CheckboxesPage;

import static page.HomePage.Links.CHECKBOXES_PAGE;

public class CheckboxesTest extends BaseTest {

    @Test
    public void ensureThatCheckboxesWorksFine() {
        var checkboxesPage = (CheckboxesPage) homePage
                .navigateTo(CHECKBOXES_PAGE);

        checkboxesPage
                .assertOnPageTitle("Checkboxes");

        var firstCheckboxStatus = checkboxesPage
                .getFirstCheckboxStatus();
        var secondCheckboxStatus = checkboxesPage
                .getSecondCheckboxStatus();

        checkboxesPage
                .clickOnFirstCheckbox()
                .clickOnSecondCheckbox()
                .assertThatFirstCheckboxIsChecked(!firstCheckboxStatus)
                .assertThatSecondCheckboxIsChecked(!secondCheckboxStatus)
                .clickOnFirstCheckbox()
                .assertThatFirstCheckboxIsChecked(firstCheckboxStatus)
                .assertThatSecondCheckboxIsChecked(!secondCheckboxStatus)
                .clickOnSecondCheckbox()
                .assertThatFirstCheckboxIsChecked(firstCheckboxStatus)
                .assertThatSecondCheckboxIsChecked(secondCheckboxStatus)
                .verify();
    }
}
