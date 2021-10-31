package page;

import helper.GuiAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DigestAuthenticationPage {
    private final GuiAction guiAction;

    private final By titleText = By.tagName("h3");
    private final By body = By.tagName("body");
    private final By messageLocator = By.tagName("p");

    public DigestAuthenticationPage(WebDriver driver) {
        guiAction = new GuiAction(driver);
    }

    public DigestAuthenticationPage assertOnPageTitle(String expectedTitle) {
        var actualTitle = guiAction.getTextFrom(titleText);
        guiAction.assertThat(
                "Checking the page title to be: " + expectedTitle,
                () -> assertEquals(expectedTitle, actualTitle)
        );
        return this;
    }

    public void assertThatTheLoginIsSuccess() {
        guiAction.assertThat(
                "Ensure that the login is success",
                () -> assertTrue(guiAction.getTextFrom(messageLocator).contains("Congratulations!"))
        );
    }

    public void assertThatBodyIsEmpty() {
        guiAction.assertThat(
                "Checking that the credentials are rejected",
                () -> assertTrue(guiAction.getTextFrom(body).isEmpty())
        );
    }
}
