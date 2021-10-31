package tests;

import base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.ChallengingDOMPage;


import static page.HomePage.Links.CHALLENGING_DOM_PAGE;

public class ChallengingDOMTest extends BaseTest {
    private final String PAGE_TITLE = "Challenging DOM";
    ChallengingDOMPage challengingDOMPage;

    @BeforeEach
    @Override
    public void minorSetUp() {
        super.minorSetUp();

        challengingDOMPage = (ChallengingDOMPage) homePage
                .navigateTo(CHALLENGING_DOM_PAGE);
    }

    @Test
    public void checkThatTheAnswerWillChangeAfterClickingOnFirstButton() {
        var answerBeforeClicking = challengingDOMPage
                .assertOnPageTitle(PAGE_TITLE)
                .getAnswer();

        challengingDOMPage
                .clickOnFirstButton()
                .assertThatAnswerNotEqual(answerBeforeClicking);
    }

    @Test
    public void checkThatTheAnswerWillChangeAfterClickingOnSecondButton() {
        var answerBeforeClicking = challengingDOMPage
                .assertOnPageTitle(PAGE_TITLE)
                .getAnswer();

        challengingDOMPage
                .clickOnSecondButton()
                .assertThatAnswerNotEqual(answerBeforeClicking);
    }

    @Test
    public void checkThatTheAnswerWillChangeAfterClickingOnThirdButton() {
        var answerBeforeClicking = challengingDOMPage
                .assertOnPageTitle(PAGE_TITLE)
                .getAnswer();

        challengingDOMPage
                .clickOnThirdButton()
                .assertThatAnswerNotEqual(answerBeforeClicking);
    }

    @Test
    public void checkThatClickingOnFirstEditButtonWillChangeTheURL() {
        challengingDOMPage
                .assertOnPageTitle(PAGE_TITLE)
                .clickOnEditInTheFirstRow()
                .assertThatURLContains("#edit");
    }

    @Test
    public void checkThatClickingOnFirstDeleteButtonWillChangeTheURL() {
        challengingDOMPage
                .assertOnPageTitle(PAGE_TITLE)
                .clickOnDeleteInTheFirstRow()
                .assertThatURLContains("#delete");
    }
}
