package Tests;
import Base.BaseTest;
import Pages.P07_CheckoutPage_Register;
import Utilities.DataUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC07_CheckoutPage_Register extends BaseTest {

    @Test
    public void verifyRedirectToLoginWhenNotLoggedIn() {
        driver.get(DataUtils.getProperty("Checkout.url"));

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/login"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/login"), "Expected to be redirected to login page when not logged in");
    }

    @Test
    public void verifyCheckoutPageWhenLoggedIn() {
        P07_CheckoutPage_Register checkoutPage = new P07_CheckoutPage_Register(driver);

        driver.get(DataUtils.getProperty("Checkout.url"));

        if (checkoutPage.isCartEmpty()) {
            driver.get(DataUtils.getProperty("base.url"));
            Assert.fail("Cart is empty. Redirected to Home page.");
        } else {
            if (!checkoutPage.isUserLoggedIn()) {
                checkoutPage.clickRegisterButton();

                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.urlContains("/register"));

                Assert.assertTrue(driver.getCurrentUrl().contains("/register"),
                        "Expected to be redirected to register page when clicking register button");
            } else {
                Assert.assertTrue(
                        checkoutPage.isCheckoutFormDisplayed(),
                        "Checkout form should be displayed for logged in users."
                );
            }
        }
    }
}
