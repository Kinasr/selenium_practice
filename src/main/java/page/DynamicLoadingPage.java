package page;

import helper.actions.GuiAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DynamicLoadingPage {
    private final GuiAction guiAction;
    private final WebDriver driver;

    private final By titleText = By.tagName("h3");
    private final By example1Link = By.xpath("//a[contains(text(),'Example 1')]");
    private final By example2Link = By.xpath("//a[contains(text(),'Example 2')]");

    public DynamicLoadingPage(WebDriver driver) {
        this.driver = driver;
        guiAction = new GuiAction(driver);
    }

    public DynamicLoadingPage assertOnPageTitle(String expectedTitle) {
        var actualTitle = guiAction.getTextFrom(titleText);
        guiAction.assertThat(
                "Checking the page title to be: " + expectedTitle,
                () -> assertEquals(expectedTitle, actualTitle)
        );
        return this;
    }

    public ExampleOne navigateToExampleOne() {
        guiAction.clickOn(example1Link);
        return new ExampleOne(driver);
    }

    public ExampleTwo navigateToExampleTwo() {
        guiAction.clickOn(example2Link);
        return new ExampleTwo(driver);
    }

    public static class ExampleOne{
        private final GuiAction guiAction;

        private final By titleText = By.tagName("h3");
        private final By startButton = By.cssSelector("#start button");
        private final By resultText = By.id("finish");

        private ExampleOne(WebDriver driver) {
            guiAction = new GuiAction(driver);
        }

        public ExampleOne assertOnPageTitle(String expectedTitle) {
            var actualTitle = guiAction.getTextFrom(titleText);
            guiAction.assertThat(
                    "Checking the page title to be: " + expectedTitle,
                    () -> assertEquals(expectedTitle, actualTitle)
            );
            return this;
        }

        public ExampleOne clickOnStart() {
            guiAction.clickOn(startButton);
            return this;
        }

        public ExampleOne assertResultIsDisplayed() {
            guiAction.assertThat(
                    "Ensure that the result is displayed",
                    () -> assertTrue(guiAction.isDisplayed(resultText))
            );
            return this;
        }
    }

    public static class ExampleTwo{
        private final GuiAction guiAction;

        private final By titleText = By.tagName("h3");
        private final By startButton = By.cssSelector("#start button");
        private final By resultText = By.id("finish");

        private ExampleTwo(WebDriver driver) {
            guiAction = new GuiAction(driver);
        }

        public ExampleTwo assertOnPageTitle(String expectedTitle) {
            var actualTitle = guiAction.getTextFrom(titleText);
            guiAction.assertThat(
                    "Checking the page title to be: " + expectedTitle,
                    () -> assertEquals(expectedTitle, actualTitle)
            );
            return this;
        }

        public ExampleTwo clickOnStart() {
            guiAction.clickOn(startButton);
            return this;
        }

        public ExampleTwo assertResultIsDisplayed() {
            guiAction.assertThat(
                    "Ensure that the result is displayed",
                    () -> assertTrue(guiAction.isDisplayed(resultText))
            );
            return this;
        }
    }
}
