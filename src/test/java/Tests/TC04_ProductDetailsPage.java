package Tests;


import Base.BaseTest;
import Pages.P04_ProductDetailsPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Utilities.DataUtils;


import java.time.Duration;


public class TC04_ProductDetailsPage extends BaseTest {
    P04_ProductDetailsPage productDetailsPage;

    @Test(description = "Test Product Details Page - verify elements and quantity buttons behavior")
    public void verifyProductDetailsPage() {
        productDetailsPage = new P04_ProductDetailsPage(driver);
        SoftAssert softAssert = new SoftAssert();

        driver.get(DataUtils.getProperty("ProductDetails.url"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(productDetailsPage.productName));
        softAssert.assertTrue(productDetailsPage.productName.isDisplayed(), "Product name is NOT displayed");

        softAssert.assertTrue(productDetailsPage.productPrice.isDisplayed(), "Product price is NOT displayed");

        softAssert.assertTrue(productDetailsPage.productDescription.isDisplayed(), "Product description is NOT displayed");

        softAssert.assertTrue(productDetailsPage.isColorDisplayed(), "Default color is NOT displayed");

        softAssert.assertTrue(productDetailsPage.isQuantityFieldDisplayed(), "Quantity input is NOT displayed");

        softAssert.assertTrue(productDetailsPage.isAddToCartButtonDisplayed(), "Add To Cart button is NOT displayed");

        int quantity = productDetailsPage.getQuantity();
        softAssert.assertEquals(quantity, 1, "Default quantity is NOT 1");

        softAssert.assertTrue(productDetailsPage.isMinusDisabled(), "Minus button should be disabled when quantity is 1");

        productDetailsPage.clickPlus();
        int quantityAfterPlus = productDetailsPage.getQuantity();
        softAssert.assertEquals(quantityAfterPlus, 2, "Quantity did NOT increase after clicking plus");

        softAssert.assertFalse(productDetailsPage.isMinusDisabled(), "Minus button should be enabled after increasing quantity");

        productDetailsPage.clickMinus();
        int quantityAfterMinus = productDetailsPage.getQuantity();
        softAssert.assertEquals(quantityAfterMinus, 1, "Quantity did NOT decrease after clicking minus");

        softAssert.assertTrue(productDetailsPage.isMinusDisabled(), "Minus button should be disabled when quantity returns to 1");

        productDetailsPage.clickMinus();
        int quantityAfterExtraMinus = productDetailsPage.getQuantity();
        softAssert.assertEquals(quantityAfterExtraMinus, 1, "Quantity should NOT go below 1");

        Assert.assertTrue(productDetailsPage.isAddToCartButtonDisplayed(), "Add to Cart button is not displayed");
        productDetailsPage.clickAddToCart();
        softAssert.assertAll();
        productDetailsPage.clickAddToCart();
        productDetailsPage.clickCartIconTwice();
        Assert.assertEquals(driver.getCurrentUrl(), "https://advantageonlineshopping.com/#/shoppingCart", "Failed to navigate to Cart page");

    }

}
