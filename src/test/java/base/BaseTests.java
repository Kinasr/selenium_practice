package base;

import helper.PropertyReader;
import helper.WebDriverFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import static helper.Constant.TEST_RESOURCES_PATH;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTests {
    private WebDriver driver;

    @BeforeAll
    public void majorSetUp() {
        driver = WebDriverFactory.getDriver(
                new PropertyReader(TEST_RESOURCES_PATH + "configuration/browser-config")
        );
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
