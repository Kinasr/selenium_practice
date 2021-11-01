package page;

import helper.GuiAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DropdownPage {
    private final GuiAction guiAction;

    private final By titleText = By.tagName("h3");
    private final By dropdownList = By.id("dropdown");

    public DropdownPage(WebDriver driver) {
        guiAction = new GuiAction(driver);
    }

    public DropdownPage assertOnPageTitle(String expectedTitle) {
        var actualTitle = guiAction.getTextFrom(titleText);
        guiAction.assertThat(
                "Checking the page title to be: " + expectedTitle,
                () -> assertEquals(expectedTitle, actualTitle)
        );
        return this;
    }

    public DropdownPage selectOptionByText(String option) {
        guiAction.locateDropdown(dropdownList)
                .selectByText(option);
        return this;
    }

    public void assertThatTheRightOptionIsSelected(String expectedOption) {
        var actualOption = guiAction.locateDropdown(dropdownList)
                .getSelectedOption();
        guiAction.assertThat(
                "Check that the selected option is: " + expectedOption,
                () -> assertEquals(expectedOption, actualOption)
        );
    }
}
