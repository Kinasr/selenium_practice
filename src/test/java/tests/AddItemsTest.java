package tests;

import base.BaseTests;
import helper.Constant;
import helper.JsonReader;
import org.junit.jupiter.api.*;
import page.LoginPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AddItemsTest extends BaseTests {
    private String username;
    private String password;
    private List<Object> itemsList;

    @BeforeAll
    public void localClassSetUp() {
        var reader = new JsonReader(Constant.TEST_RESOURCES_PATH + "data/add_items_data");
        username = reader.get("username").toString();
        password = reader.get("password").toString();
        itemsList = Arrays.stream(reader.get("items").toArray()).toList();
    }

    /**
     * Override the before class in the base to become before method.
     * Calling minorSetUp to open the site, and to ensure that we use the same driver instance.
     */
    @BeforeEach
    @Override
    public void majorSetUp() {
        super.majorSetUp();
        minorSetUp();
    }

    /**
     * Override the minorSetUp to delete its annotation and with keeping its main job.
     */
    @Override
    public void minorSetUp() {
        super.minorSetUp();
    }

    /**
     * Override the after class in the base to become after method.
     */
    @AfterEach
    @Override
    public void majorTearDown() {
        super.majorTearDown();
    }

    @Test
    @DisplayName("Testing the adding item to the cart functionality")
    public void addItemToCart() {
        var item = getRandomItems(1);

        new LoginPage(getDriver())
                .enterUserCredentials(username, password)
                .login()
                .addItemToCart(item.get(0))
                .assertOnNumOfItemsOnCart(1);
    }

    @Test
    @DisplayName("Testing adding tow items to the cart at the same time")
    public void addTowItemsToCart() {
        var items = getRandomItems(2);

        new LoginPage(getDriver())
                .enterUserCredentials(username, password)
                .login()
                .addItemToCart(items.get(0))
                .addItemToCart(items.get(1))
                .assertOnNumOfItemsOnCart(2);
    }

    @Test
    @DisplayName("Testing the removing item form the cart functionality")
    public void addOneItemToCartThenRemoveIt() {
        var item = getRandomItems(1);

        new LoginPage(getDriver())
                .enterUserCredentials(username, password)
                .login()
                .addItemToCart(item.get(0))
                .removeItemFormCart(item.get(0))
                .assertThatCartIsEmpty();
    }

    @Test
    @DisplayName("Testing that removing one item will not affect the other items in the cart")
    public void addTowItemsToCartThenRemoveOne() {
        var items = getRandomItems(2);

        new LoginPage(getDriver())
                .enterUserCredentials(username, password)
                .login()
                .addItemToCart(items.get(0))
                .addItemToCart(items.get(1))
                .removeItemFormCart(items.get(0))
                .assertOnNumOfItemsOnCart(1);
    }

    @Test
    @DisplayName("Testing that logout will not remove the items in the cart")
    public void addItemToCartThenLogoutAndLogin() {
        var item = getRandomItems(1);

        new LoginPage(getDriver())
                .enterUserCredentials(username, password)
                .login()
                .addItemToCart(item.get(0))
                .openBurgerMenu()
                .logout()
                .enterUserCredentials(username, password)
                .login()
                .assertOnNumOfItemsOnCart(1);
    }

    private List<String> getRandomItems(int numOfItems) {
        var list = new ArrayList<String>();

        while (list.size() < numOfItems) {
            var random = new Random();
            var item = itemsList.get(random.nextInt(itemsList.size()));
            if (!list.contains(item.toString()))
                list.add(item.toString());
        }
        return list;
    }
}