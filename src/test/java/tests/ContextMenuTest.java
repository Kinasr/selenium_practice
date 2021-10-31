package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import page.ContextMenuPage;

import static page.HomePage.Links.CONTEXT_MENU_PAGE;

public class ContextMenuTest extends BaseTest {

    @Test
    public void rightClickOnHotSpotToShowAlert() {
        var contextMenuPage = (ContextMenuPage) homePage
                .navigateTo(CONTEXT_MENU_PAGE);

        contextMenuPage
                .assertOnPageTitle("Context Menu")
                .rightClickOnContext()
                .assertThatAlertIsPresent();
    }
}
