package page;

import helper.GuiAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPage {
    private final WebDriver driver;
    private final GuiAction guiAction;
    private final By textFieldUsername = By.id("user-name");
    private final By textFieldPassword = By.id("password");
    private final By buttonLogin = By.id("login-button");
    private final By errorLabel = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        guiAction = new GuiAction(driver);
    }

    public LoginPage enterUserCredentials(String username, String password) {
        guiAction.sendTextTo(textFieldUsername, username);
        guiAction.sendTextTo(textFieldPassword, password);
        return new LoginPage(driver);
    }

    public InventoryPage login() {
        guiAction.clickOn(buttonLogin);
        return new InventoryPage(driver);
    }

    public LoginPage assertOnErrorMessage(String msg) {
        assertEquals(msg, guiAction.getTextFrom(errorLabel));
        return this;
    }
}
