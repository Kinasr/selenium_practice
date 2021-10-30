package page;

import helper.GuiAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

public class HomePage {
    private final WebDriver driver;
    private final GuiAction guiAction;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        guiAction = new GuiAction(driver);
    }

    public Object navigateTo(Links link) {
        guiAction.clickOn(link.getLocatorByLinkText());

        return link.getNavigator().apply(driver);
    }

    public enum Links {
        AB_TESTING_PAGE("A/B Testing", ABTestingPage::new),
        ADD_REMOVE_ELEMENTS_PAGE("Add/Remove Elements", AddRemoveElementsPage::new),
        BASIC_AUTH_PAGE("Basic Auth", BasicAuthPage::new),
        BROKEN_IMAGES_PAGE("Broken Images", BrokenImagesPage::new);
//        CHALLENGING_DOM_PAGE("Challenging DOM", ChallengingDOMPage::new),
//        CHECKBOXES_PAGE("Checkboxes", CheckboxesPage::new),
//        CONTEXT_MENU_PAGE("Context Menu", ContextMenuPage::new),
//        DIGEST_AUTHENTICATION_PAGE("Digest Authentication", DigestAuthenticationPage::new),
//        DISAPPEARING_ELEMENTS_PAGE("Disappearing Elements", DisappearingElementsPage::new),
//        DRAG_AND_DROP_PAGE("Drag and Drop", DragAndDropPage::new),
//        DROPDOWN("Dropdown", DropdownPage::new),
//        DYNAMIC_CONTENT_PAGE("Dynamic Content", DynamicContentPage::new),
//        DYNAMIC_CONTROLS_PAGE("Dynamic Controls", DynamicControlsPage::new),
//        DYNAMIC_LOADING_PAGE("Dynamic Loading", DynamicLoadingPage::new),
//        ENTRY_AD_PAGE("Entry Ad", EntryAdPage::new),
//        EXIT_INTENT_PAGE("Exit Intent", ExitIntentPage::new),
//        FILE_DOWNLOAD_PAGE("File Download", FileDownloadPage::new),
//        FILE_UPLOAD_PAGE("File Upload", FileUploadPage::new),
//        FLOATING_MENU_PAGE("Floating Menu", FloatingMenuPage::new),
//        FORGOT_PASSWORD_PAGE("Forgot Password", ForgotPasswordPage::new),
//        FORM_AUTHENTICATION_PAGE("Form Authentication", FormAuthenticationPage::new),
//        FRAMES_PAGE("Frames", FramesPage::new),
//        GEOLOCATION_PAGE("Geolocation", GeolocationPage::new),
//        HORIZONTAL_SLIDER_PAGE("Horizontal Slider", HorizontalSliderPage::new),
//        HOVERS_PAGE("Hovers", HoversPage::new),
//        INFINITE_SCROLL_PAGE("Infinite Scroll", InfiniteScrollPage::new),
//        INPUTS_PAGE("Inputs", InputsPage::new),
//        JQUERY_UI_MENUS_PAGE("JQuery UI Menus", JQueryUIMenusPage::new),
//        JAVASCRIPT_ALERTS_PAGE("JavaScript Alerts", JavaScriptAlertsPage::new),
//        JAVASCRIPT_ON_LOAD_EVENT_ERROR_PAGE("JavaScript onload event error", JavaScriptOnLoadEventErrorPage::new),
//        KEY_PRESSES_PAGE("Key Presses", KeyPressesPage::new),
//        LARGE_AND_DEEP_DOM_PAGE("Large & Deep DOM", LargeAndDeepDOMPage::new),
//        MULTIPLE_WINDOWS_PAGE("Multiple Windows", MultipleWindowsPage::new),
//        NESTED_FRAMES_PAGE("Nested Frames", NestedFramesPage::new),
//        NOTIFICATION_MESSAGES_PAGE("Notification Messages", NotificationMessagesPage::new),
//        REDIRECT_LINK_PAGE("Redirect Link", RedirectLinkPage::new),
//        SECURE_FILE_DOWNLOAD_PAGE("Secure File Download", SecureFileDownloadPage::new),
//        SHADOW_DOM_PAGE("Shadow DOM", ShadowDOMPage::new),
//        SHIFTING_CONTENT_PAGE("Shifting Content", ShiftingContentPage::new),
//        SLOW_RESOURCES_PAGE("Slow Resources", SlowResourcesPage::new),
//        SORTABLE_DATA_TABLES_PAGE("Sortable Data Tables", SortableDataTablesPage::new),
//        STATUS_CODES_PAGE("Status Codes", StatusCodesPage::new),
//        TYPOS_PAGE("Typos", TyposPage::new),
//        WYSIWYG_EDITOR_PAGE("WYSIWYG Editor", WYSIWYGEditorPage::new);

        private final String locator;
        private final Function<WebDriver, Object> navigator;

        Links(String locator, Function<WebDriver, Object> navigator) {
            this.locator = locator;
            this.navigator = navigator;
        }

        public By getLocatorByLinkText() {
            return By.linkText(locator);
        }
        public Function<WebDriver, Object> getNavigator() {
            return navigator;
        }
    }
}