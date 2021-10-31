package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import page.BrokenImagesPage;

import static page.HomePage.Links.BROKEN_IMAGES_PAGE;

public class BrokenImagesTest extends BaseTest {

    @Test
    public void checkThatAllImageAppearsProperly() {
        var brokenImagesPage = (BrokenImagesPage) homePage.navigateTo(BROKEN_IMAGES_PAGE);

        brokenImagesPage
                .assertOnPageTitle("Broken Images")
                .assertThatNoBrokenImages();
    }
}
