package page;

import helper.GuiAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryPage extends BurgerMenu {
    private final WebDriver driver;
    private final GuiAction guiAction;
    private final By pageTitle = By.className("title");
    private final By shoppingCartBadge = By.className("shopping_cart_badge");
    private final String addItemButtonId = "add-to-cart-";
    private final String removeItemButtonId = "remove-";

    public InventoryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        guiAction = new GuiAction(driver);
    }

    public InventoryPage addItemToCart(String itemName) {
        var formattedItemName = itemName.replaceAll(" ", "-").toLowerCase();
        var addItemButton = By.id(addItemButtonId + formattedItemName);
        guiAction.clickOn(addItemButton);
        return this;
    }

    public InventoryPage removeItemFormCart(String itemName) {
        var formattedItemName = itemName.replaceAll(" ", "-").toLowerCase();
        var removeItemButton = By.id(removeItemButtonId + formattedItemName);
        guiAction.clickOn(removeItemButton);
        return this;
    }

    public InventoryPage assertOnPageTitle(String expectedTitle) {
        assertEquals(expectedTitle, guiAction.getTextFrom(pageTitle));
        return this;
    }

    public InventoryPage assertOnNumOfItemsOnCart(int expectedNumOfItems) {
        assertEquals(expectedNumOfItems, Integer.parseInt(guiAction.getTextFrom(shoppingCartBadge)));
        return this;
    }

    public InventoryPage assertThatCartIsEmpty() {
        assertTrue(guiAction.ensureElementDoesNotExist(shoppingCartBadge));
        return this;
    }
}
