package page;

import helper.actions.GuiAction;
import model.VerifyRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicContentPage {
    private final GuiAction guiAction;

    private final By titleText = By.tagName("h3");
    private final By imagesLocator = By.cssSelector(".large-2.columns");
    private final By paragraphs = By.cssSelector(".large-10.columns");

    public DynamicContentPage(WebDriver driver) {
        guiAction = new GuiAction(driver);
    }

    public DynamicContentPage assertOnPageTitle(String expectedTitle) {
        var actualTitle = guiAction.getTextFrom(titleText);
        guiAction.assertThat(
                "Checking the page title to be: " + expectedTitle,
                () -> assertEquals(expectedTitle, actualTitle)
        );
        return this;
    }

    public DynamicContentPage ensureThatAllParagraphsHaveContent() {
        var pElements = guiAction.getElements(paragraphs);
        var verifications = new ArrayList<VerifyRecord>();

        for (var i = 1; i < pElements.size(); i++) {
            var hasText = !guiAction.getTextFrom(pElements.get(i)).isEmpty();
            verifications.add(new VerifyRecord(
               "Ensure that the paragraph with index {" + i + "} is not empty",
                    hasText,
                    () -> assertTrue(hasText)
            ));
        }

        guiAction.performVerification(verifications);
        return this;
    }
    public DynamicContentPage ensureThatAllImagesAppear() {
        var images = guiAction.getElements(imagesLocator);
        var verifications = new ArrayList<VerifyRecord>();

        for (WebElement image : images) {
            verifications.add(new VerifyRecord(
                    "Check that [" + image.getAttribute("src") + "] is Displayed",
                    guiAction.isDisplayed(image),
                    () -> assertTrue(guiAction.isDisplayed(image))
            ));
        }
        guiAction.performVerification(verifications);

        return this;
    }
}