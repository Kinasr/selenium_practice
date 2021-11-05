package page;

import helper.actions.GuiAction;
import model.VerifyRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DragAndDropPage {
    private final GuiAction guiAction;

    private final By titleText = By.tagName("h3");
    private final By columnA = By.id("column-a");
    private final By columnB = By.id("column-b");
    private final By firstColumnPlace = By.cssSelector("#columns div:first-child");
    private final By secondColumnPlace = By.cssSelector("#columns div:last-child");

    public DragAndDropPage(WebDriver driver) {
        guiAction = new GuiAction(driver);
    }

    public DragAndDropPage assertOnPageTitle(String expectedTitle) {
        var actualTitle = guiAction.getTextFrom(titleText);
        guiAction.assertThat(
                "Checking the page title to be: " + expectedTitle,
                () -> assertEquals(expectedTitle, actualTitle)
        );
        return this;
    }

    public DragAndDropPage changeColumnsPlaces() {
        guiAction.dragAndDrop(columnA, columnB);

        return this;
    }

    public void assertThatTheColumnsOrderIs(ColumnsOrder order) {
        var fColumnText = guiAction.getTextFrom(firstColumnPlace);
        var sColumnText = guiAction.getTextFrom(secondColumnPlace);

        var exForFirstColumn = order.equals(ColumnsOrder.A_TO_B) ? "A" : "B";
        var exForSecondColumn = order.equals(ColumnsOrder.A_TO_B) ? "B" : "A";

        guiAction.performVerification(List.of(
                new VerifyRecord(
                        "Check the text in the first column to be: " + exForFirstColumn,
                        fColumnText.equals(exForFirstColumn),
                        () -> assertEquals(exForFirstColumn, fColumnText)
                ),
                new VerifyRecord(
                        "Check the text in the second column to be: " + exForSecondColumn,
                        sColumnText.equals(exForSecondColumn),
                        () -> assertEquals(exForSecondColumn, sColumnText)
                )
        ));
    }

    public enum ColumnsOrder{
        A_TO_B, B_TO_A
    }
}
