package base;

import helper.PropertyReader;
import helper.WebDriverFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import page.HomePage;

import java.io.File;
import java.io.IOException;

import static helper.Constant.TEST_RESOURCES_PATH;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    private WebDriver driver;
    protected HomePage homePage;

    @BeforeAll
    public void majorSetUp() {
        driver = WebDriverFactory.getDriver(
                new PropertyReader(TEST_RESOURCES_PATH + "configuration/browser-config")
        );
        homePage = new HomePage(driver);
    }

    @BeforeEach
    public void minorSetUp() {
        var url = new PropertyReader(TEST_RESOURCES_PATH + "configuration/test-configurations")
                .getProperty("base-url");
        driver.get(url);
    }

    @AfterEach
    public void minorTearDown() {}

    @AfterAll
    public void majorTearDown() {
        WebDriverFactory.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
