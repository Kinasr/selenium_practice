package page;

import helper.GuiAction;
import model.VerifyRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BrokenImagesPage {
    private final WebDriver driver;
    private final GuiAction guiAction;

    private final By titleText = By.tagName("h3");
    private final By imagesLocator = By.tagName("img");

    public BrokenImagesPage(WebDriver driver) {
        this.driver = driver;
        guiAction = new GuiAction(driver);
    }

    public BrokenImagesPage assertOnPageTitle(String expectedTitle) {
        var actualTitle = guiAction.getTextFrom(titleText);
        guiAction.assertThat(
                "Checking the page title to be: " + expectedTitle,
                () -> assertEquals(expectedTitle, actualTitle)
        );
        return this;
    }

    public BrokenImagesPage assertThatNoBrokenImages() {
        var images = guiAction.getElements(imagesLocator);
        var verifications = new ArrayList<VerifyRecord>();

        for (WebElement image : images) {
            image = guiAction.waitForVisibility(image);
            WebElement img = image;
            verifications.add(new VerifyRecord(
                    "Check that [" + image.getAttribute("src") + "] is not broken",
                    !img.getAttribute("naturalWidth").equals("0"),
                    () -> assertNotEquals("0", img.getAttribute("naturalWidth"))
            ));
        }

        guiAction.performVerification(verifications);
        return this;
    }
}
