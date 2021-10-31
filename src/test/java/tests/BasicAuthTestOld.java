package tests;

import base.BaseTest;
import helper.PropertyReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import page.BasicAuthPage;

import static helper.Constant.TEST_RESOURCES_PATH;

public class BasicAuthTestOld extends BaseTest {
    private String url;

    @Override
    public void majorSetUp() {}

    @BeforeEach
    @Override
    public void minorSetUp() {
        super.majorSetUp();
        url = new PropertyReader(TEST_RESOURCES_PATH + "configuration/test-configurations")
                .getProperty("base-url");
    }

    @AfterEach
    @Override
    public void minorTearDown() {
        super.majorTearDown();
    }

    @Override
    public void majorTearDown() {}

    @Test
    public void testValidLogin() {
        url = url.replace("https://", "https://admin:admin@") + "basic_auth";
        getDriver().get(url);

        new BasicAuthPage(getDriver())
                .assertOnPageTitle("Basic Auth");
    }

    @ParameterizedTest(name = "{index} => Login with username: {0} and password: {1}")
    @CsvSource(value = {" , ", "wrong, wrong", "admin, wrong", "wrong, admin"})
    public void testInvalidLogin(String username, String password) {
        var auth = (username == null ? "" : username) + ":" + (password == null ? "" : password);
        url = url.replace("https://", "https://" + auth + "@") + "basic_auth";
        getDriver().get(url);

        new BasicAuthPage(getDriver())
                .assertThatBodyIsEmpty();
    }
}
