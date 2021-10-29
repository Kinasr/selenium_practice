package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GuiAction {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public GuiAction(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(Constant.getWaitTime()));
    }

    public void clickOn(By by) {
        WebElement element = driver.findElement(by);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void sendTextTo(By by, String text) {
        WebElement element = driver.findElement(by);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    public String getTextFrom(By by) {
        WebElement element = driver.findElement(by);
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public boolean ensureElementDoesNotExist(By by) {
        List<WebElement> elements = driver.findElements(by);
        return elements.isEmpty();
    }
}
