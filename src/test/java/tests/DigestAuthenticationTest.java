package tests;

import base.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import page.DigestAuthenticationPage;

import static page.HomePage.Links.DIGEST_AUTHENTICATION_PAGE;

public class DigestAuthenticationTest extends BaseTest {

    @Override
    public void majorSetUp() {}

    @BeforeEach
    @Override
    public void minorSetUp() {
        super.majorSetUp();
        super.minorSetUp();
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
        ((HasAuthentication) getDriver()).register(
                () -> new UsernameAndPassword("admin", "admin"));

        var digestAuthenticationPage = (DigestAuthenticationPage) homePage
                .navigateTo(DIGEST_AUTHENTICATION_PAGE);

        digestAuthenticationPage
                .assertOnPageTitle("Digest Auth")
                .assertThatTheLoginIsSuccess();
    }

    @ParameterizedTest(name = "{index} => Login with username: {0} and password: {1}")
    @CsvSource(value = {"'', ''", "wrong, wrong", "admin, wrong", "wrong, admin"})
    public void testInvalidLogin(String username, String password) {
        ((HasAuthentication) getDriver()).register(
                () -> new UsernameAndPassword(username, password));

        var digestAuthenticationPage = (DigestAuthenticationPage) homePage
                .navigateTo(DIGEST_AUTHENTICATION_PAGE);

        digestAuthenticationPage
                .assertThatBodyIsEmpty();
    }
}