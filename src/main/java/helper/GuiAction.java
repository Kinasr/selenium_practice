package helper;

import io.qameta.allure.model.Status;
import model.VerifyRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.MyReport;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class GuiAction {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;
    private List<VerifyRecord> verifications = new ArrayList<>();

    public GuiAction(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(Constant.getWaitTime()));
        actions = new Actions(driver);
    }

    public int getElementsSize(By by) {
        return driver.findElements(by).size();
    }

    public List<WebElement> getElements(By by) {
        return driver.findElements(by);
    }

    public WebElement waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
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

    public String getURL() {
        return driver.getCurrentUrl();
    }

    public String getTextFrom(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public String getTextFrom(By by) {
        WebElement element = driver.findElement(by);
        return getTextFrom(element);
    }

    // -------------------------------------------------- Actions --------------------------------------------------
    public void rightClickOn(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        actions.contextClick(element).perform();
    }

    public void rightClickOn(By by) {
        rightClickOn(
                driver.findElement(by)
        );
    }

    public void dragAndDrop(WebElement fromElement, WebElement toElement) {
        wait.until(ExpectedConditions.and(ExpectedConditions.visibilityOf(fromElement),
                ExpectedConditions.visibilityOf(toElement)));

        var javaScript = """
                var src=arguments[0],tgt=arguments[1];
                var dataTransfer={
                    dropEffect:'',
                    effectAllowed:'all',
                    files:[],
                    items:{},
                    types:[],
                    setData:
                    function(format,data){
                        this.items[format]=data;
                        this.types.append(format);
                        },
                    getData:function(format){
                        return this.items[format];
                        },
                    clearData:function(format){}
                    };
                var emit=function(event,target){
                    var evt=document.createEvent('Event');
                    evt.initEvent(event,true,false);
                    evt.dataTransfer=dataTransfer;target.dispatchEvent(evt);
                    };
                emit('dragstart',src);
                emit('dragenter',tgt);
                emit('dragover',tgt);
                emit('drop',tgt);
                emit('dragend',src);
                """;

        ((JavascriptExecutor)driver).executeScript(javaScript, fromElement, toElement);
    }

    public void dragAndDrop(By from, By to) {
        dragAndDrop(
                driver.findElement(from),
                driver.findElement(to)
        );
    }

    // -------------------------------------------------- Alert --------------------------------------------------
    public void sendTextToAlert(String text) {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().sendKeys(text);
    }

    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public boolean isAlertPresent(){
        try{
            var alert = wait.until(ExpectedConditions.alertIsPresent());
            if(alert!=null){
                return true;
            }else{
                throw new Throwable();
            }
        }
        catch (Throwable e) {
            return false;
        }
    }

    public boolean isElementPresent(By by) {
        var elements = driver.findElements(by);
        return !elements.isEmpty();
    }

    public boolean isSelected(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.isSelected();
    }

    public boolean isSelected(By by) {
        WebElement element = driver.findElement(by);
        return isSelected(element);
    }

    //TODO: modify it to match the test runner "now it's for JUnit5"
    public void assertThat(String message, Runnable runnable) {
        var stepId = MyReport.startStep(this.getClass().getSimpleName(), message);
        runnable.run();
        MyReport.updateStepToBePassed(stepId);
    }

    //TODO: modify it to match the test runner "now it's for JUnit5"
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

    //TODO: modify it to match the test runner "now it's for JUnit5"
    public GuiAction startVerification(VerifyRecord record) {
        if (verifications.isEmpty()) verifications = new ArrayList<>();

        verifications.add(record);

        return this;
    }

    //TODO: modify it to match the test runner "now it's for JUnit5"
    public void verify() {
        if (!verifications.isEmpty()) {
            performVerification(verifications);
            verifications.clear();
        }
    }
}
