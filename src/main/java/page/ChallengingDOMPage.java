package page;

import helper.actions.GuiAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

import static org.junit.jupiter.api.Assertions.*;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class ChallengingDOMPage {
    private final GuiAction guiAction;

    private final By titleText = By.tagName("h3");
    private final By scriptLocator = By.tagName("script");
    private final By firstButton = By.cssSelector(".large-2 a:first-child");
    private final By secondButton = By.cssSelector(".large-2 a:nth-child(3)");
    private final By thirdButton = By.cssSelector(".large-2 a:nth-child(5)");
    private final RelativeLocator.RelativeBy editButton = with(By.tagName("a"));
    private final RelativeLocator.RelativeBy deleteButton = with(By.tagName("a"));
    private final By phaedrum0 =  By.xpath("//td[text()='Phaedrum0']");

    public ChallengingDOMPage(WebDriver driver) {
        guiAction = new GuiAction(driver);
    }

    public ChallengingDOMPage assertOnPageTitle(String expectedTitle) {
        var actualTitle = guiAction.getTextFrom(titleText);
        guiAction.assertThat(
                "Checking the page title to be: " + expectedTitle,
                () -> assertEquals(expectedTitle, actualTitle)
        );
        return this;
    }

    public ChallengingDOMPage clickOnFirstButton() {
        guiAction.clickOn(firstButton);
        return this;
    }

    public ChallengingDOMPage clickOnSecondButton() {
        guiAction.clickOn(secondButton);
        return this;
    }

    public ChallengingDOMPage clickOnThirdButton() {
        guiAction.clickOn(thirdButton);
        return this;
    }

    public ChallengingDOMPage clickOnEditInTheFirstRow() {
        guiAction.clickOn(editButton.toRightOf(phaedrum0));
        return this;
    }

    public ChallengingDOMPage clickOnDeleteInTheFirstRow() {
        guiAction.clickOn(deleteButton.toRightOf(editButton.toRightOf(phaedrum0)));
        return this;
    }

    public String getAnswer() {
        var canvasText = "";
        var scripts = guiAction.getElements(scriptLocator);

        for (var script : scripts) {
            var focusText = script.getAttribute("innerHTML");
            if (focusText.contains("canvas.strokeText")) {
                canvasText = focusText.substring(focusText.indexOf("Answer"), focusText.indexOf("',"));
                break;
            }
        }
        return canvasText;
    }

    public void assertThatAnswerNotEqual(String unwanted) {
        guiAction.assertThat(
                "Checking that the answer value not equal: " + unwanted,
                () -> assertNotEquals(unwanted, getAnswer())
        );
    }

    public void assertThatURLContains(String text) {
        guiAction.assertThat(
                "Checking that the URL contains: " + text,
                () -> assertTrue(guiAction.getURL().contains(text))
        );
    }
}
