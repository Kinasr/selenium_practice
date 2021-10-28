package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import base.BaseTests;
import page.LoginPage;

public class LoginTests extends BaseTests{

    @ParameterizedTest(name = "{index} -> Login with username: {0} and password: {1}")
    @DisplayName("Testing Login Functionality with Valid Credentials")
    @MethodSource(value = "data_providers.DataProviders#gerValidUserCredentials")
    public void testValidLogin(String username, String password, String nextPageTitle) {
        new LoginPage(getDriver())
                .enterUserCredentials(username, password)
                .login()
                .assertOnPageTitle(nextPageTitle);
    }

    @ParameterizedTest(name = "{index} -> Login with username: {0} and password: {1}")
    @DisplayName("Testing Login Functionality with Invalid Credentials")
    @MethodSource(value = "data_providers.DataProviders#geInValidUserCredentials")
    public void testInvalidLogin(String username, String password, String message) {
        var loginPage = new LoginPage(getDriver());
        loginPage
                .enterUserCredentials(username, password)
                .login();
        loginPage.assertOnErrorMessage(message);
    }
}
