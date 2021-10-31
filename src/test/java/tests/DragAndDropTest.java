package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import page.DragAndDropPage;

import static page.DragAndDropPage.ColumnsOrder.*;
import static page.HomePage.Links.DRAG_AND_DROP_PAGE;

public class DragAndDropTest extends BaseTest {
    private final String PAGE_TITLE = "Drag and Drop";

    @Test
    public void dragAndDropAToB() {
        ((DragAndDropPage) homePage.navigateTo(DRAG_AND_DROP_PAGE))
                .assertOnPageTitle(PAGE_TITLE)
                .changeColumnsPlaces()
                .assertThatTheColumnsOrderIs(B_TO_A);
    }

    @Test
    public void dragAdnDropAToBThenBToA() {
        ((DragAndDropPage) homePage.navigateTo(DRAG_AND_DROP_PAGE))
                .assertOnPageTitle(PAGE_TITLE)
                .changeColumnsPlaces()
                .changeColumnsPlaces()
                .assertThatTheColumnsOrderIs(A_TO_B);
    }
}
