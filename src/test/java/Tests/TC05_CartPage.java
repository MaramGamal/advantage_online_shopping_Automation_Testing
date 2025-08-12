package Tests;
import Base.BaseTest;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.P04_ProductDetailsPage;
import Pages.P05_CartPage;
import static org.testng.Assert.assertTrue;


public class TC05_CartPage extends BaseTest {


    @Test(description = "Check if cart is empty or not and navigate to checkout if not empty")
    public void checkCartAndProceedToCheckout() {
        driver.get(DataUtils.getProperty("Cart.url"));

        if (cartPage.isCartEmpty()) {
            LogsUtils.info("The cart is empty.");
        } else {
            LogsUtils.info("The cart contains at least one product.");

            Assert.assertTrue(cartPage.isProductNameDisplayed(), "The product name is not displayed.");
            Assert.assertTrue(cartPage.isProductColorDisplayed(), " The product color is not visible.");
            Assert.assertTrue(cartPage.isProductPriceDisplayed(), " The price is not displayed.");
            Assert.assertTrue(cartPage.isProductQuantityDisplayed(), " The quantity is not visible.");
            Assert.assertTrue(cartPage.isRemoveButtonDisplayed(), " The Remove button is not visible.");
            Assert.assertTrue(cartPage.isEditButtonDisplayed(), "The Edit button is not visible.");

            Assert.assertTrue(cartPage.isCheckoutButtonDisplayed(), " The Checkout button is not visible.");

            cartPage.clickCheckoutButton();

            Assert.assertTrue(driver.getCurrentUrl().contains("orderPayment"), "Didn't navigate to the Checkout page.");
            LogsUtils.info(" Successfully navigated to the Checkout page.");
        }
    }
}