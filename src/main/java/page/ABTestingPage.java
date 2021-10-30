package page;

import helper.GuiAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ABTestingPage {
    private final WebDriver driver;
    private final GuiAction guiAction;

    private final By titleText = By.tagName("h3");

    public ABTestingPage(WebDriver driver) {
        this.driver = driver;
        guiAction = new GuiAction(driver);
    }

    public ABTestingPage assertOnPageTitle(String expectedTitle) {
        var actualTitle = guiAction.getTextFrom(titleText);
        guiAction.assertThat(
                "Checking the page title to be: " + expectedTitle,
                () -> assertEquals(actualTitle, expectedTitle)
        );
        return this;
    }
}
