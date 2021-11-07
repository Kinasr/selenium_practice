package tests;

import base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.FileUploadPage;

import static helper.Constant.TEST_RESOURCES_PATH;
import static page.HomePage.Links.FILE_UPLOAD_PAGE;

public class FileUploadTest extends BaseTest {
    private FileUploadPage fileUploadPage;

    @BeforeEach
    @Override
    public void minorSetUp() {
        super.minorSetUp();
        fileUploadPage = (FileUploadPage) homePage
                .navigateTo(FILE_UPLOAD_PAGE);
    }

    @Test
    public void testNavigationToTheFileUploadPage() {
        fileUploadPage
                .assertOnPageTitle("File Uploader");
    }

    @Test
    public void uploadImage() {
        fileUploadPage
                .selectFile(System.getProperty("user.dir") + "/" + TEST_RESOURCES_PATH + "data/test.png")
                .uploadFile()
                .assertOnPageTitle("File Uploaded!");
    }
}
