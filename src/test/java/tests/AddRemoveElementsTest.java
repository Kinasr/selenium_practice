package tests;

import base.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import page.AddRemoveElementsPage;

import static page.HomePage.Links.ADD_REMOVE_ELEMENTS_PAGE;

public class AddRemoveElementsTest extends BaseTest {

    @ParameterizedTest(name = "{index} => Add {0} of elements then remove {1} of elements and {2} will remain")
    @CsvSource(value = {"1, 1, 0", "2, 1, 1", "5, 2, 3"})
    public void testAddingAndRemovingElements(int added, int removed, int expectedToRemain) {
        var addRemoveElementsPage = (AddRemoveElementsPage) homePage.navigateTo(ADD_REMOVE_ELEMENTS_PAGE);

        addRemoveElementsPage
                .assertOnPageTitle("Add/Remove Elements")
                .addNOfElements(added)
                .removeNOfElements(removed)
                .assertOnNumberOfAddedElements(expectedToRemain);
    }
}
