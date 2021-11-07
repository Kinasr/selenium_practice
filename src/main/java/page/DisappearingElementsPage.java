package page;

import helper.actions.GuiAction;
import model.VerifyRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DisappearingElementsPage {
    private final WebDriver driver;
    private final GuiAction guiAction;

    private final By titleText = By.tagName("h3");
    private final By homeButton = By.linkText("Home");
    private final By aboutButton = By.linkText("About");
    private final By contactUsButton = By.linkText("Contact Us");
    private final By portfolioButton = By.linkText("Portfolio");
    private final By galleryButton = By.linkText("Gallery");

    public DisappearingElementsPage(WebDriver driver) {
        this.driver = driver;
        guiAction = new GuiAction(driver);
    }

    public DisappearingElementsPage assertOnPageTitle(String expectedTitle) {
        var actualTitle = guiAction.getTextFrom(titleText);
        guiAction.assertThat(
                "Checking the page title to be: " + expectedTitle,
                () -> assertEquals(expectedTitle, actualTitle)
        );
        return this;
    }

    public HomePage goToHomePage() {
        guiAction.clickOn(homeButton);
        return new HomePage(driver);
    }

    public void ensureThatAllButtonsArePresent() {
        var isHomePresent = guiAction.isPresent(homeButton);
        var isAboutPresent = guiAction.isPresent(aboutButton);
        var isContactUsPresent = guiAction.isPresent(contactUsButton);
        var isPortfolioPresent = guiAction.isPresent(portfolioButton);
        var isGalleryPresent = guiAction.isPresent(galleryButton);
        guiAction.performVerification(List.of(
                new VerifyRecord(
                        "Ensure that the Home button is present",
                        isHomePresent,
                        () -> assertTrue(isHomePresent)
                ),
                new VerifyRecord(
                        "Ensure that the About button is present",
                        isAboutPresent,
                        () -> assertTrue(isAboutPresent)
                ),
                new VerifyRecord(
                        "Ensure that the Contact us button is present",
                        isContactUsPresent,
                        () -> assertTrue(isContactUsPresent)
                ),
                new VerifyRecord(
                        "Ensure that the Portfolio button is present",
                        isPortfolioPresent,
                        () -> assertTrue(isPortfolioPresent)
                ),
                new VerifyRecord(
                        "Ensure that the Gallery button is present",
                        isGalleryPresent,
                        () -> assertTrue(isGalleryPresent)
                )
        ));
    }
}
