package page;

import helper.actions.GuiAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DynamicControlsPage {
    private final GuiAction guiAction;

    private final By titleText = By.cssSelector("h4:first-child");
    private final By addRemoveButton = By.cssSelector("#checkbox-example button");
    private final By enableDisableButton = By.cssSelector("#input-example button");
    private final By checkbox = By.id("checkbox");
    private final By textField = By.cssSelector("#input-example input");
    private final By checkboxMessage = By.cssSelector("#checkbox-example #message");
    private final By enableDisableMessage = By.cssSelector("#input-example #message");
    private final By loadingCheckbox = By.cssSelector("#checkbox-example #loading");
    private final By loadingInput = By.cssSelector("#input-example #loading");

    public DynamicControlsPage(WebDriver driver) {
        guiAction = new GuiAction(driver);
    }

    public DynamicControlsPage assertOnPageTitle(String expectedTitle) {
        var actualTitle = guiAction.getTextFrom(titleText);
        guiAction.assertThat(
                "Checking the page title to be: " + expectedTitle,
                () -> assertEquals(expectedTitle, actualTitle)
        );
        return this;
    }

    public DynamicControlsPage clickOnAddRemoveButton() {
        guiAction.clickOn(addRemoveButton);
        return this;
    }

    public DynamicControlsPage clickOnEnableDisableButton() {
        guiAction.clickOn(enableDisableButton);
        return this;
    }

    public DynamicControlsPage assertThatCheckboxIsAppear() {
        guiAction.assertThat(
                "Ensure that the checkbox is appear",
                () -> assertTrue(guiAction.isElementPresent(checkbox))
        );
        return this;
    }

    public DynamicControlsPage assertTheTextFieldStatus(boolean isEnabled) {
        var x = guiAction.isEnabled(textField);

        guiAction.assertThat(
                "Assert that the enable stats for the text field is: " + isEnabled,
                () -> assertEquals(isEnabled, guiAction.isEnabled(textField))
        );
        return this;
    }

    public DynamicControlsPage assertOnCheckboxMessage(String message) {
        guiAction.assertThat(
                "Check the checkbox message is: " + message,
                () -> assertEquals(message, guiAction.getTextFrom(checkboxMessage))
        );
        return this;
    }

    public DynamicControlsPage assertOnTextFieldMessage(String message) {
        guiAction.assertThat(
                "Check the text field message is: " + message,
                () -> assertEquals(message, guiAction.getTextFrom(enableDisableMessage))
        );
        return this;
    }
}