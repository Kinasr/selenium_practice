package page;

import helper.GuiAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddRemoveElementsPage {
    private final WebDriver driver;
    private final GuiAction guiAction;

    private final By titleText = By.tagName("h3");
    private final By addElementButton = By.cssSelector(".example button");
    private final By elementsButtons = By.className("added-manually");

    public AddRemoveElementsPage(WebDriver driver) {
        this.driver = driver;
        guiAction = new GuiAction(driver);
    }

    public AddRemoveElementsPage assertOnPageTitle(String expectedTitle) {
        var actualTitle = guiAction.getTextFrom(titleText);
        guiAction.assertThat(
                "Checking the page title to be: " + expectedTitle,
                () -> assertEquals(expectedTitle, actualTitle)
        );
        return this;
    }

    public AddRemoveElementsPage addElement() {
        guiAction.clickOn(addElementButton);
        return this;
    }

    public AddRemoveElementsPage addNOfElements(int n) {
        for (var i = 0; i < n; i++) addElement();
        return this;
    }

    public AddRemoveElementsPage removeElement() {
        var elements = guiAction.getElements(elementsButtons);
        guiAction.clickOn(elements.get(0));
        return this;
    }

    public AddRemoveElementsPage removeNOfElements(int n) {
        for (var i = 0; i < n; i++) removeElement();

        return this;
    }

    public AddRemoveElementsPage assertOnNumberOfAddedElements(int expectedSize) {
        var actualSize = guiAction.getElementsSize(elementsButtons);
        guiAction.assertThat(
                "Checking the number of added elements to be: " + expectedSize,
                () -> assertEquals(expectedSize, actualSize)
        );

        return this;
    }
}
