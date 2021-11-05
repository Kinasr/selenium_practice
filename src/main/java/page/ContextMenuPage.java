package page;

import helper.actions.GuiAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContextMenuPage {
    private final GuiAction guiAction;

    private final By titleText = By.tagName("h3");
    private final By context = By.id("hot-spot");

    public ContextMenuPage(WebDriver driver) {
        guiAction = new GuiAction(driver);
    }

    public ContextMenuPage assertOnPageTitle(String expectedTitle) {
        var actualTitle = guiAction.getTextFrom(titleText);
        guiAction.assertThat(
                "Checking the page title to be: " + expectedTitle,
                () -> assertEquals(expectedTitle, actualTitle)
        );
        return this;
    }

    public ContextMenuPage rightClickOnContext() {
        guiAction.rightClickOn(context);
        return this;
    }

    public void assertThatAlertIsPresent() {
        guiAction.assertThat(
                "Ensure that the alert is present",
                () -> assertTrue(guiAction.isAlertPresent())
        );
    }
}
