package helper;

import io.qameta.allure.model.Status;
import model.VerifyRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.MyReport;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class GuiAction {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private List<VerifyRecord> verifications;

    public GuiAction(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(Constant.getWaitTime()));
    }

    public int getElementsSize(By by) {
        return driver.findElements(by).size();
    }

    public List<WebElement> getElements(By by) {
        return driver.findElements(by);
    }

    public void clickOn(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void clickOn(By by) {
        WebElement element = driver.findElement(by);
        clickOn(element);
    }

    public void sendTextTo(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    public void sendTextTo(By by, String text) {
        WebElement element = driver.findElement(by);
        sendTextTo(element, text);
    }

    public String getTextFrom(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public String getTextFrom(By by) {
        WebElement element = driver.findElement(by);
        return getTextFrom(element);
    }

    public WebElement waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public boolean ensureElementDoesNotExist(By by) {
        List<WebElement> elements = driver.findElements(by);
        return elements.isEmpty();
    }

    public void sendTextToAlert(String text) {
        driver.switchTo().alert();
    }

    public void assertThat(String message, Runnable runnable) {
        var stepId = MyReport.startStep(this.getClass().getSimpleName(), message);
        runnable.run();
        MyReport.updateStepToBePassed(stepId);
    }

    public void performVerification(List<VerifyRecord> records) {
        var assertions = new ArrayList<Executable>();

        for (VerifyRecord record : records) {
            var stepId = MyReport.startStep(this.getClass().getSimpleName(), record.getMessage());

            assertions.add(record.getExecutable());

            if (record.condition().orElse(true))
                MyReport.updateStepStatus(stepId, Status.PASSED);
            else
                MyReport.updateStepStatus(stepId, Status.FAILED);

            MyReport.stopStep(stepId);
        }

        Assertions.assertAll(assertions.stream());
    }

    public GuiAction startVerification(VerifyRecord record) {
        if (verifications.isEmpty()) verifications = new ArrayList<>();

        verifications.add(record);

        return this;
    }

    public void verify() {
        if (!verifications.isEmpty()) {
            performVerification(verifications);
            verifications.clear();
        }
    }
}
