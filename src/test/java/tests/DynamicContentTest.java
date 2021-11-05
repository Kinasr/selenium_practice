package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import page.DynamicContentPage;

import static page.HomePage.Links.DYNAMIC_CONTENT_PAGE;

public class DynamicContentTest extends BaseTest {

    @Test
    public void ensureThatAllPageContentAppearProperly() {
        ((DynamicContentPage) homePage.navigateTo(DYNAMIC_CONTENT_PAGE))
                .assertOnPageTitle("Dynamic Content")
                .ensureThatAllParagraphsHaveContent()
                .ensureThatAllImagesAppear();
    }
}
