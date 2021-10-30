package page;

import helper.GuiAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasicAuthPage {
    private final WebDriver driver;
    private final GuiAction guiAction;

    private final By titleText = By.tagName("h3");
    private final By body = By.tagName("body");

    public BasicAuthPage(WebDriver driver) {
        this.driver = driver;
        guiAction = new GuiAction(driver);
    }

    public BasicAuthPage assertOnPageTitle(String expectedTitle) {
        var actualTitle = guiAction.getTextFrom(titleText);
        guiAction.assertThat(
                "Checking the page title to be: " + expectedTitle,
                () -> assertEquals(expectedTitle, actualTitle)
        );
        return this;
    }

    public BasicAuthPage assertThatBodyIsEmpty() {
        guiAction.assertThat(
                "Checking that the credentials are rejected",
                () -> assertTrue(guiAction.getTextFrom(body).isEmpty())
        );
        return this;
    }
}
