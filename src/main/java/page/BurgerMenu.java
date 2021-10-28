package page;

import helper.GuiAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BurgerMenu {
    private final WebDriver driver;
    private final GuiAction guiAction;
    private final By burgerMenuButton = By.id("react-burger-menu-btn");
    private final By logoutButton = By.id("logout_sidebar_link");

    public BurgerMenu(WebDriver driver) {
        this.driver = driver;
        guiAction = new GuiAction(driver);
    }

    public BurgerMenu openBurgerMenu() {
        guiAction.clickOn(burgerMenuButton);
        return new BurgerMenu(driver);
    }

    public LoginPage logout() {
        guiAction.clickOn(logoutButton);
        return new LoginPage(driver);
    }
}
