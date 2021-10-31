package page;

import helper.GuiAction;
import model.VerifyRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckboxesPage {
    private final GuiAction guiAction;

    private final By titleText = By.tagName("h3");
    private final By firstCheckbox = By.cssSelector("#checkboxes input:first-child");
    private final By secondCheckbox = By.cssSelector("#checkboxes input:last-child");

    public CheckboxesPage(WebDriver driver) {
        guiAction = new GuiAction(driver);
    }

    public CheckboxesPage assertOnPageTitle(String expectedTitle) {
        var actualTitle = guiAction.getTextFrom(titleText);
        guiAction.assertThat(
                "Checking the page title to be: " + expectedTitle,
                () -> assertEquals(expectedTitle, actualTitle)
        );
        return this;
    }

    public CheckboxesPage clickOnFirstCheckbox() {
        guiAction.clickOn(firstCheckbox);
        return this;
    }

    public CheckboxesPage clickOnSecondCheckbox() {
        guiAction.clickOn(secondCheckbox);
        return this;
    }

    public boolean getFirstCheckboxStatus() {
        return guiAction.isSelected(firstCheckbox);
    }

    public boolean getSecondCheckboxStatus() {
        return guiAction.isSelected(secondCheckbox);
    }

    public CheckboxesPage assertThatFirstCheckboxIsChecked(boolean status) {
        var actualStatus = guiAction.isSelected(firstCheckbox);
        guiAction.startVerification(
                new VerifyRecord(
                        "Check the first checkbox status to be: " + status,
                        actualStatus == status,
                        () -> assertEquals(status, actualStatus)
                )
        );
        return this;
    }

    public CheckboxesPage assertThatSecondCheckboxIsChecked(boolean status) {
        var actualStatus = guiAction.isSelected(secondCheckbox);
        guiAction.startVerification(
                new VerifyRecord(
                        "Check the second checkbox status to be: " + status,
                        actualStatus == status,
                        () -> assertEquals(status, actualStatus)
                )
        );
        return this;
    }

    public void verify() {
        guiAction.verify();
    }
}
