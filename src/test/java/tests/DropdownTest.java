package tests;

import base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DropdownPage;

import static page.HomePage.Links.DROPDOWN;

public class DropdownTest extends BaseTest {
    private final String PAGE_TITLE = "Dropdown List";
    private final String option1 = "Option 1";
    private final String option2 = "Option 2";
    private DropdownPage dropdownPage;

    @BeforeEach
    @Override
    public void minorSetUp() {
        super.minorSetUp();
        dropdownPage = (DropdownPage) homePage
                .navigateTo(DROPDOWN);
    }

    @Test
    public void selectOption1() {
        dropdownPage
                .assertOnPageTitle(PAGE_TITLE)
                .selectOptionByText(option1)
                .assertThatTheRightOptionIsSelected(option1);
    }

    @Test
    public void selectOption2() {
        dropdownPage
                .assertOnPageTitle(PAGE_TITLE)
                .selectOptionByText(option2)
                .assertThatTheRightOptionIsSelected(option2);
    }

    @Test
    public void selectOption1ThenOption2() {
        dropdownPage
                .assertOnPageTitle(PAGE_TITLE)
                .selectOptionByText(option1)
                .selectOptionByText(option2)
                .assertThatTheRightOptionIsSelected(option2);
    }

    @Test
    public void selectOption2ThenOption1() {
        dropdownPage
                .assertOnPageTitle(PAGE_TITLE)
                .selectOptionByText(option2)
                .selectOptionByText(option1)
                .assertThatTheRightOptionIsSelected(option1);
    }
}
